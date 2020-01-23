package com.intech.task.ui.search_song

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.intech.task.R
import com.intech.task.databinding.FragmentSearchSongBinding
import com.intech.task.diff_util.SongListDiffUtil
import com.intech.task.model.data_class.Result
import kotlinx.android.synthetic.main.toolbar_with_search_view.*

class SearchSongFragment : Fragment() {

    private lateinit var viewModel: SearchSongViewModel
    private lateinit var binding: FragmentSearchSongBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_song, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SearchSongViewModel::class.java)
        binding.viewModel = viewModel

        setupRecycleView()
        setupSearchView()
        observeSearchResult()
        observeSearchError()
        setItemClickListener()
    }

    private fun setupRecycleView() {
        binding.songRecycleView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = viewModel.getSongAdapter()
        }
    }

    private fun setupSearchView() {
        viewModel.observeSearchView(toolbar_search_view)
    }

    /**
     * Наблюдает за результатами поиска и обновляет интрфейс
     */
    private fun observeSearchResult() {
        viewModel.searchResultLiveData.observe(this, Observer{ detailSongList ->
            updateUI(detailSongList)
        })
    }

    /**
     * Наблюдает за ошибками при поиске и показвает уведомление пользователю
     */
    private fun observeSearchError() {
        viewModel.searchErrorLiveData.observe(this, Observer { errorMessage ->
            Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG).show()
        })
    }

    /**
     * Обновляет список песен
     */
    private fun updateUI(detailSongList: MutableList<Result>) {
        val adapter =  viewModel.getSongAdapter()
        val songDiffUtil = SongListDiffUtil(adapter.getData(), detailSongList)
        val diffResult = DiffUtil.calculateDiff(songDiffUtil, false)

        adapter.setData(detailSongList)
        diffResult.dispatchUpdatesTo(adapter)
    }

    /**
     * Обработчик нажатий на песню
     */
    private fun setItemClickListener() {
        viewModel.getSongAdapter().itemClick = { detailSong ->
            // Скрываем клавиатуру
            toolbar_search_view.clearFocus()

            viewModel.songOnClick(findNavController(), detailSong)
        }
    }
}