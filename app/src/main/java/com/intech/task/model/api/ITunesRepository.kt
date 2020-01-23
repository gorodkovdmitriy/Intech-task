package com.intech.task.model.api

import com.intech.task.model.data_class.SearchSongResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ITunesRepository {

    @GET("search")
    fun searchSong(@Query("term") keyword: String): Observable<SearchSongResult>

}