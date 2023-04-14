package com.sb2318.musicwiki.services

import com.sb2318.musicwiki.model.TagResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DataInterface {

    @GET("2.0/")
    fun getGenres(@Query("method") method: String, @Query("api_key") apiKey: String,@Query("format") format:String): Call<TagResponse>


}