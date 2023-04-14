package com.sb2318.musicwiki.model

import com.google.gson.annotations.SerializedName

data class ArtistResponse(
    val topartists: Topartists
)

data class Topartists(
    @SerializedName("@attr")
    val attr: Attr,
    val artist: List<Artist>
)

