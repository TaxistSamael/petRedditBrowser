package com.kek.redditfeed.feed.presentation.model

import com.kek.redditfeed.feed.domain.model.FeedPost

class FeedPostListViewModel(
  val posts: List<FeedPostViewModel> = emptyList(),
  val cursor: String = ""
)