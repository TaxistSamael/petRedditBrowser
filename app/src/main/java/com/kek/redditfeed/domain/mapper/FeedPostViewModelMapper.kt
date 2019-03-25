package com.kek.redditfeed.domain.mapper

import com.kek.redditfeed.base.domain.Mapper
import com.kek.redditfeed.domain.model.FeedPost
import com.kek.redditfeed.presentation.model.FeedPostViewModel
import javax.inject.Inject

class FeedPostViewModelMapper @Inject constructor(): Mapper<FeedPost, FeedPostViewModel>() {

  override fun reverse(to: FeedPostViewModel) = throw NotImplementedError()

  override fun map(from: FeedPost): FeedPostViewModel {
    with(from) {
      return FeedPostViewModel(id, title, author, createdUtc, numComments, url, thumbnail)
    }
  }
}