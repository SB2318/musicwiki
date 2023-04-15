package com.sb2318.musicwiki.model.artist

import com.google.gson.annotations.SerializedName
import com.sb2318.musicwiki.model.album.Image
import com.sb2318.musicwiki.model.tag.Toptags


data class ArtistInfoResponse(
    val artist: Artist
)

data class Artist(
    val bio: Bio,
    val image: List<Image>,
    val mbid: String,
    val name: String,
    val ontour: String,
    val similar: Similar,
    val stats: Stats,
    val streamable: String,
    val tags: Toptags,
    val url: String
)

data class Similar(
    val artist: List<ArtistX>
)

data class Stats(
    val listeners: String,
    val playcount: String
)
data class ArtistX(
    val image: List<Image>,
    val name: String,
    val url: String
)

data class Bio(
    val content: String,
    val links: Links,
    val published: String,
    val summary: String
)

data class Links(
    val link: Link
)

data class Link(
    @SerializedName("#text")
    val text: String,
    val href: String,
    val rel: String
)