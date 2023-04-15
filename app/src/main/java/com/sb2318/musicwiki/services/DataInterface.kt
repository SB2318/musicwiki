package com.sb2318.musicwiki.services

import com.sb2318.musicwiki.model.TrackResponse
import com.sb2318.musicwiki.model.album.AlbumInfoResponse
import com.sb2318.musicwiki.model.album.AlbumResponse
import com.sb2318.musicwiki.model.artist.ArtistInfoResponse
import com.sb2318.musicwiki.model.artist.ArtistResponse
import com.sb2318.musicwiki.model.tag.TagInfoResponse
import com.sb2318.musicwiki.model.tag.TagResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DataInterface {

    @GET("2.0/")
    fun getGenres(@Query("method") method: String, @Query("api_key") apiKey: String,@Query("format") format:String): Call<TagResponse>

    @GET("2.0/")
    fun getTagInfo(@Query("method")method: String, @Query("tag")tagname:String,@Query("api_key") apiKey: String,@Query("format") format:String)
    : Call<TagInfoResponse>


    @GET("2.0/")
    fun getTopAlbums(@Query("method")method: String,@Query("tag")tagname:String,@Query("api_key") apiKey: String,@Query("format") format:String)
    :Call<AlbumResponse>

    @GET("2.0/")
    fun getTopArtists(@Query("method")method: String,@Query("tag")tagname:String,@Query("api_key") apiKey: String,@Query("format") format:String)
            :Call<ArtistResponse>

    @GET("2.0/")
    fun getTopTracks(@Query("method")method: String,@Query("tag")tagname:String,@Query("api_key") apiKey: String,@Query("format") format:String)
            :Call<TrackResponse>

    @GET("2.0/")
    fun getAlbumInfo(@Query("method") method: String, @Query("api_key") apiKey: String,@Query("artist") artistName:String,
            @Query("album")albumName:String,@Query("format") format:String):Call<AlbumInfoResponse>

    @GET("2.0")
    fun getArtistInfo(
        @Query("method") method: String, @Query("artist") artistName:String, @Query("api_key") apiKey: String,
        @Query("format") format:String
    ):Call<ArtistInfoResponse>

}