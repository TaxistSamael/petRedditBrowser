package com.kek.redditfeed.feed.data.net

import com.kek.redditfeed.feed.data.model.FeedResponse
import io.reactivex.Single
import javax.inject.Inject

//todo: scope
class FeedApiImpl @Inject constructor(
  private val feedService: FeedService
) : FeedApi {

  override fun getTopPosts(count: Int, after: String): Single<FeedResponse> {
    return feedService.getTopPosts(count, after)
  }
}