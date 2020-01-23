package com.intech.task.ui.search_song

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.intech.task.R
import com.intech.task.adapter.SongListAdapter
import com.intech.task.application.App
import com.intech.task.model.api.ITunesRepository
import com.intech.task.model.data_class.Result
import com.intech.task.model.data_class.SearchSongResult
import com.intech.task.ui.music_player.PlayerViewModel
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchSongViewModel: ViewModel() {

    @Inject lateinit var iTunesRepository: ITunesRepository

    private var disposable: Disposable? =  null
    private var songListAdapter: SongListAdapter? = null

    var isLoading = ObservableField<Boolean>(false)
    var searchResultLiveData = MutableLiveData<MutableList<Result>>()
    var searchErrorLiveData = MutableLiveData<String>()

    init {
        // Внедряем зависимости
        App.dagger.inject(searchSongViewModel = this)
    }

    override fun onCleared() {
        super.onCleared()

        // Отменяем все запросы при уничтожении ViewModel
        disposable?.dispose()
    }

    /**
     * Поиск песен в iTunes
     */
    fun searchSong(keyword: String) {
        isLoading.set(true)

        disposable = iTunesRepository.searchSong(keyword)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { next -> showResult(next) },
                { error -> showError(error) }
            )
    }

    /**
     *  Возвращает адаптер для recycleView
     */
    fun getSongAdapter(): SongListAdapter {
        if (songListAdapter == null) {
            songListAdapter = SongListAdapter()
        }

        return songListAdapter!!
    }

    /**
     * Отслеживает изменения текста в Search View
     */
    @SuppressLint("CheckResult")
    fun observeSearchView(toolbarSearchView: SearchView) {

        Observable.create(ObservableOnSubscribe<String> { subscriber ->
            toolbarSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                    subscriber.onNext(newText!!)
                    return false
                }
                override fun onQueryTextSubmit(query: String?): Boolean {
                    subscriber.onNext(query!!)
                    return false
                }
            })

        })
            .filter { text-> text.length > 4 }
            .filter { text -> text.isNotBlank() }
            .map { text -> text.toLowerCase(Locale.ROOT).trim() }
            .debounce (500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribe { text ->
                searchSong(keyword = text)
            }
    }

    private fun showError(error: Throwable) {
        isLoading.set(false)
        searchErrorLiveData.value = error.localizedMessage
    }

    private fun showResult(responce: SearchSongResult) {
        isLoading.set(false)
        searchResultLiveData.value = responce.results
    }

    /**
     * Обработчик нажатий на песню, перход к экрану плеера
     */
    fun songOnClick(navController: NavController, detailSong: Result) {
        val bundle = Bundle()
        bundle.putParcelable(PlayerViewModel.DETAIL_SONG_BUNDLE_KEY, detailSong)

        navController.navigate(R.id.player_fragment, bundle)
    }

}