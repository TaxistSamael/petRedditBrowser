package com.kek.redditfeed.main.feed.presentation.mapper

import com.kek.redditfeed.base.Mapper
import com.kek.redditfeed.main.feed.domain.model.FeedPost
import com.kek.redditfeed.main.feed.presentation.model.FeedPostViewModel
import javax.inject.Inject

class FeedPostViewModelMapper @Inject constructor(): Mapper<FeedPost, FeedPostViewModel>() {

  override fun reverse(to: FeedPostViewModel) = throw NotImplementedError()

  override fun map(from: FeedPost): FeedPostViewModel {
    with(from) {
      return FeedPostViewModel(id, title, author, createdUtc, numComments, url, thumbnail)
    }
  }
}