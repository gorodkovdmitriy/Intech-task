package com.intech.task.model.data_class

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Result(
//    @SerializedName("wrapperType")
//    @Expose
//    val wrapperType: String,
//
//    @SerializedName("kind")
//    @Expose
//    val kind: String,
//
//    @SerializedName("artistId")
//    @Expose
//    val artistId: Int,
//
//    @SerializedName("collectionId")
//    @Expose
//    val collectionId: Int,
//
    @SerializedName("trackId")
    @Expose
    val trackId: Int,

    @SerializedName("artistName")
    @Expose
    val artistName: String,
//
//    @SerializedName("collectionName")
//    @Expose
//    val collectionName: String,

    @SerializedName("trackName")
    @Expose
    val trackName: String,

//    @SerializedName("collectionCensoredName")
//    @Expose
//    val collectionCensoredName: String,
//
//    @SerializedName("trackCensoredName")
//    @Expose
//    val trackCensoredName: String,
//
//    @SerializedName("artistViewUrl")
//    @Expose
//    val artistViewUrl: String,
//
//    @SerializedName("collectionViewUrl")
//    @Expose
//    val collectionViewUrl: String,
//
    @SerializedName("trackViewUrl")
    @Expose
    val trackViewUrl: String,
//
    @SerializedName("previewUrl")
    @Expose
    val previewUrl: String,
//
//    @SerializedName("artworkUrl30")
//    @Expose
//    val artworkUrl30: String,
//
//    @SerializedName("artworkUrl60")
//    @Expose
//    val artworkUrl60: String,
//
    @SerializedName("artworkUrl100")
    @Expose
    val artworkUrl100: String,
//
//    @SerializedName("collectionPrice")
//    @Expose
//    val collectionPrice: Float,
//
//    @SerializedName("trackPrice")
//    @Expose
//    val trackPrice: Float,
//
//    @SerializedName("releaseDate")
//    @Expose
//    val releaseDate: String,
//
//    @SerializedName("collectionExplicitness")
//    @Expose
//    val collectionExplicitness: String,
//
//    @SerializedName("trackExplicitness")
//    @Expose
//    val trackExplicitness: String,
//
//    @SerializedName("discCount")
//    @Expose
//    val discCount: Int,
//
//    @SerializedName("discNumber")
//    @Expose
//    val discNumber: Int,
//
//    @SerializedName("trackCount")
//    @Expose
//    val trackCount: Int,
//
//    @SerializedName("trackNumber")
//    @Expose
//    val trackNumber: Int,
//
    @SerializedName("trackTimeMillis")
    @Expose
    val trackTimeMillis: Int
//
//    @SerializedName("country")
//    @Expose
//    val country: String,
//
//    @SerializedName("currency")
//    @Expose
//    val currenc: String,
//
//    @SerializedName("primaryGenreName")
//    @Expose
//    val primaryGenreName: String,
//
//    @SerializedName("isStreamable")
//    @Expose
//    val isStreamable: Boolean
): Parcelable {
}