package com.kek.redditfeed.main.feed.domain.model

class FeedPost(
    val id: String = "",
    val title: String = "",
    val author: String = "",
    val createdUtc: Long = 0L,
    val numComments: Int = 0,
    val url: String = "",
    val thumbnail: String = ""
)