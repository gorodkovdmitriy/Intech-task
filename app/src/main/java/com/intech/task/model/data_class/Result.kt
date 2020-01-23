package com.intech.task.model.data_class

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Result(

    @SerializedName("trackId")
    @Expose
    val trackId: Int,

    @SerializedName("artistName")
    @Expose
    val artistName: String,

    @SerializedName("trackName")
    @Expose
    val trackName: String,

    @SerializedName("trackViewUrl")
    @Expose
    val trackViewUrl: String,

    @SerializedName("previewUrl")
    @Expose
    val previewUrl: String,

    @SerializedName("artworkUrl100")
    @Expose
    val artworkUrl100: String,

    @SerializedName("trackTimeMillis")
    @Expose
    val trackTimeMillis: Int

): Parcelable {}