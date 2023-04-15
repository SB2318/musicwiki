package com.sb2318.musicwiki.model.album

import com.google.gson.annotations.SerializedName
import com.sb2318.musicwiki.model.artist.Artist

data class AlbumResponse(
    val albums: Albums
)

data class Albums(
    @SerializedName("@attr")
    val attr: Attr,
    val album: List<Album>
)
data class Album(
    @SerializedName("@attr")
    val attr: AttrX,
    val artist: Artist,
    val image: List<Image>,
    val mbid: String,
    val name: String,
    val url: String
)

data class Attr(
    val page: String,
    val perPage: String,
    val tag: String,
    val total: String,
    val totalPages: String
)
data class AttrX(
    val rank: String
)

data class Image(
    @SerializedName("#text")
    val text: String,
    val size: String
)