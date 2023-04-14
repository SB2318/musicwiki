package com.sb2318.musicwiki.model

data class TagInfoResponse(
    val tag: TagInfo
)

data class TagInfo(
    val name: String,
    val reach: Int,
    val total: Int,
    val wiki: Wiki
)

data class Wiki(
    val content: String,
    val summary: String
)