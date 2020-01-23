package com.intech.task.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.intech.task.databinding.ItemSongBinding
import com.intech.task.model.data_class.Result

class SongListAdapter: RecyclerView.Adapter<SongListAdapter.ViewHolder>() {

    var itemClick: ((detailSong: Result)-> Unit)? = null
    private var detailSongList: MutableList<Result> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSongBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = detailSongList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(detailSongList.get(position))
    }

    fun setData(detailSongList: MutableList<Result>) {
        this.detailSongList = detailSongList
    }

    fun getData(): MutableList<Result> {
        return detailSongList
    }

    inner class ViewHolder(private val binding: ItemSongBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(detailSong: Result) {
            binding.detailSong = detailSong
            binding.executePendingBindings()

            binding.root.setOnClickListener { itemClick?.invoke(detailSong) }
        }
    }
}