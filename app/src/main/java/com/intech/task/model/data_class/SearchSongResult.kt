package com.intech.task.model.data_class

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchSongResult(

    @SerializedName("resultCount")
    @Expose
    val resultCount: Int,

    @SerializedName("results")
    @Expose
    val results: MutableList<Result>
) {
}