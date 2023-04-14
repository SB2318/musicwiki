package com.sb2318.musicwiki.model
import com.google.gson.annotations.SerializedName


data class TrackResponse(
    val tracks: Tracks
)
data class Tracks(
    @SerializedName("@attr")
    val attr: Attr,
    val track: List<Track>
)

data class Track(
    @SerializedName("@attr")
    val attr: AttrX,
    val artist: Artist,
    val duration: String,
    val image: List<Image>,
    val mbid: String,
    val name: String,
    val streamable: Streamable,
    val url: String
)

data class Streamable(
    @SerializedName("#text")
    val text: String,
    val fulltrack: String
)