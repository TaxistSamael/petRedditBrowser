package com.kek.redditfeed.feed.presentation.mapper

import com.kek.redditfeed.base.Mapper
import com.kek.redditfeed.feed.domain.model.FeedPost
import com.kek.redditfeed.feed.presentation.model.FeedPostViewModel
import javax.inject.Inject

class FeedPostViewModelMapper @Inject constructor(): Mapper<FeedPost, FeedPostViewModel>() {
  override fun reverse(to: FeedPostViewModel): FeedPost {
    throw NotImplementedError()
  }

  override fun map(from: FeedPost): FeedPostViewModel {
    with(from) {
      return FeedPostViewModel(id, title, author, createdUtc, numComments, url)
    }
  }
}