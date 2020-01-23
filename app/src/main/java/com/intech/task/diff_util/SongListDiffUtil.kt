package com.intech.task.diff_util

import androidx.recyclerview.widget.DiffUtil
import com.intech.task.model.data_class.Result

class SongListDiffUtil(private val oldList: MutableList<Result>,
                       private  val newList: MutableList<Result>): DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldSong = oldList.get(oldItemPosition)
        val newSong = newList.get(newItemPosition)

        return oldSong.trackId == newSong.trackId
    }

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldSong = oldList.get(oldItemPosition)
        val newSong = newList.get(newItemPosition)

        return oldSong.equals(newSong)
    }
}