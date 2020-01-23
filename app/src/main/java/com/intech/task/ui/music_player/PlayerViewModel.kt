package com.intech.task.ui.music_player

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.intech.task.adapter.DescriptionAdapter
import com.intech.task.application.App
import com.intech.task.model.data_class.Result
import javax.inject.Inject


class PlayerViewModel(bundle: Bundle) : ViewModel() {

    @Inject lateinit var applicationContext: Context

    companion object {
        val DETAIL_SONG_BUNDLE_KEY = "detailSongBundleKey"
    }

    val playerReadyLiveData = MutableLiveData<SimpleExoPlayer>()
    val detailSongLiveData = MutableLiveData<Result>()

    private var player: SimpleExoPlayer? = null
    private var playerNotificationManager: PlayerNotificationManager? = null

    init {
        App.dagger.inject(playerViewModel = this)

        bundle.getParcelable<Result>(DETAIL_SONG_BUNDLE_KEY)?.let { detailSong ->
            detailSongLiveData.value = detailSong
            initializePlayer(detailSong)
        }
    }

    override fun onCleared() {
        super.onCleared()

        // Останавливаем воспроизведение
        cleanPlayer()
    }

    /**
     * Инициализирует плеер
     */
    private fun initializePlayer(detailSong: Result) {

        player = SimpleExoPlayer.Builder(applicationContext).build()
        player!!.prepare( ProgressiveMediaSource.Factory(DefaultHttpDataSourceFactory("player"))
            .createMediaSource(Uri.parse(detailSong.previewUrl)), true, false)
        player!!.setPlayWhenReady(false)

        setPlayerIntoView(player!!)

        // Настраиваем уведомление
       playerNotificationManager = PlayerNotificationManager(
           applicationContext,
           "player",
           123,
           getDescriptionAdapter(detailSong))

        playerNotificationManager?.setPlayer(player)
    }

    /**
     * очищает плеер
     */
    private fun cleanPlayer() {
        playerNotificationManager?.setPlayer(null)
        player?.release()
        player = null
    }

    private fun setPlayerIntoView(player: SimpleExoPlayer) { playerReadyLiveData.value = player }

    private fun getDescriptionAdapter(detailSong: Result) = DescriptionAdapter(detailSong)
}
