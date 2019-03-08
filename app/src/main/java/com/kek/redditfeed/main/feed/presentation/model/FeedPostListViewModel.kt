package com.kek.redditfeed.main.feed.presentation.model

class FeedPostListViewModel(
  val posts: List<FeedPostViewModel> = emptyList(),
  val cursor: String = ""
)