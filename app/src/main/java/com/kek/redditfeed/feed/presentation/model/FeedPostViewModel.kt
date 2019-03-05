package com.kek.redditfeed.feed.presentation.model

class FeedPostViewModel(
  val id: String = "",
  val title: String = "",
  val author: String = "",
  val createdUtc: Long = 0L,
  val numComments: Int = 0,
  val url: String = ""
)