package com.kek.redditfeed.feed.data.repository

import com.kek.redditfeed.feed.data.mapper.FeedResponseMapper
import com.kek.redditfeed.feed.data.net.FeedApi
import com.kek.redditfeed.feed.domain.model.FeedPostList
import com.kek.redditfeed.feed.domain.repository.FeedRepository
import io.reactivex.Single
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor(
  private val feedApi: FeedApi,
  private val feedResponseMapper: FeedResponseMapper
) : FeedRepository {

  override fun getTopPosts(count: Int, after: String): Single<FeedPostList> {
    return feedApi.getTopPosts(count, after).map { response ->
      feedResponseMapper.map(response)
    }
  }
}