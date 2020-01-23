package com.intech.task.factory

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.intech.task.ui.music_player.PlayerViewModel

class ViewModelFactory(val bundle: Bundle): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass == PlayerViewModel::class.java) {
            return PlayerViewModel(bundle) as T
        }

        return super.create(modelClass)
    }
}