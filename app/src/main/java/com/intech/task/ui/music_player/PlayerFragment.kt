package com.intech.task.ui.music_player

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.intech.task.R
import com.intech.task.databinding.PlayerFragmentBinding
import com.intech.task.factory.ViewModelFactory
import kotlinx.android.synthetic.main.player_fragment.*

class PlayerFragment : Fragment() {

    private lateinit var viewModel: PlayerViewModel
    private lateinit var binding: PlayerFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.player_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // ViewModel
        viewModel = ViewModelProviders.of(this, ViewModelFactory(arguments!!))
            .get(PlayerViewModel::class.java)

        setOnClicks()
        obserWhenSongDetailLoading()
        observeWhenPlayerReady()
    }

    private fun obserWhenSongDetailLoading() {
        viewModel.detailSongLiveData.observe(this, Observer { detailSong ->
            binding.detailSong = detailSong
        })
    }

    private fun observeWhenPlayerReady() {
        viewModel.playerReadyLiveData.observe(this, Observer { player ->
            player_view.setPlayer(player)
        })
    }

    private fun setOnClicks() {
        // Обрабочик кнопки назад
        back_button.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}
