package com.kek.redditfeed.feed.domain.model

//todo: make wrapper class for cursor
class FeedPostList(
  val posts: List<FeedPost> = emptyList(),
  val cursor: String = ""
)