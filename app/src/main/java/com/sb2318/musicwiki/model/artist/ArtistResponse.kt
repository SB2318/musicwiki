package com.sb2318.musicwiki.model.artist

import com.google.gson.annotations.SerializedName
import com.sb2318.musicwiki.model.album.Attr

data class ArtistResponse(
    val topartists: Topartists
)

data class Topartists(
    @SerializedName("@attr")
    val attr: Attr,
    val artist: List<Artist>
)

