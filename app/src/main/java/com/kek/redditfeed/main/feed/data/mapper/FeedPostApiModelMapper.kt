package com.kek.redditfeed.main.feed.data.mapper

import com.kek.redditfeed.base.domain.Mapper
import com.kek.redditfeed.main.feed.data.model.FeedPostApiModel
import com.kek.redditfeed.main.feed.domain.model.FeedPost
import javax.inject.Inject

class FeedPostApiModelMapper @Inject constructor() : Mapper<FeedPostApiModel, FeedPost>() {
  override fun reverse(to: FeedPost): FeedPostApiModel {
    throw NotImplementedError()
  }

  override fun map(from: FeedPostApiModel): FeedPost {
    with(from) {
      return FeedPost(id, title, author, createdUtc, numComments, url, thumbnail)
    }
  }
}