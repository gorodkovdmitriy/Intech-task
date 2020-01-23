package com.intech.task.di.component

import com.intech.task.di.module.AppModule
import com.intech.task.di.module.NetworkModule
import com.intech.task.ui.music_player.PlayerViewModel
import com.intech.task.ui.search_song.SearchSongViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = arrayOf(AppModule::class, NetworkModule::class))
interface AppComponent {

     fun inject(searchSongViewModel: SearchSongViewModel)
     fun inject(playerViewModel: PlayerViewModel)
}