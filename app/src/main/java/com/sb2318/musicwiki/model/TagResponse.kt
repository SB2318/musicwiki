package com.sb2318.musicwiki.model

data class TagResponse(
    val toptags: Toptags
)
data class Toptags(
    val tag: List<Tag>
)

data class Tag(
    val count: String,
    val name: String,
    val url: String
)
