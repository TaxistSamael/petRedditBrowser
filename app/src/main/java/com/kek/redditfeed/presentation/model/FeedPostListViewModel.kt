package com.kek.redditfeed.presentation.model

class FeedPostListViewModel(
  val posts: List<FeedPostViewModel> = emptyList(),
  val cursor: String = ""
)