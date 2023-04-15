package com.sb2318.musicwiki.model.album

import com.sb2318.musicwiki.model.tag.Toptags
import com.sb2318.musicwiki.model.Tracks
import com.sb2318.musicwiki.model.tag.Wiki


data class AlbumInfoResponse(
    val album: AlbumInfo
)
data class AlbumInfo(
    val artist: String,
    val image: List<Image>,
    val listeners: String,
    val mbid: String,
    val name: String,
    val playcount: String,
    val tags: Toptags,
    val tracks: Tracks,
    val url: String,
    val wiki: Wiki
)


