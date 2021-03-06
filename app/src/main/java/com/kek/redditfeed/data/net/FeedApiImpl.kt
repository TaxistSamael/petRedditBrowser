package com.kek.redditfeed.data.net

import com.kek.redditfeed.data.model.FeedResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeedApiImpl @Inject constructor(
  private val feedService: FeedService
) : FeedApi {

  override fun getTopPosts(count: Int, after: String): Single<FeedResponse> {
    return feedService.getTopPosts(count, after)
  }
}