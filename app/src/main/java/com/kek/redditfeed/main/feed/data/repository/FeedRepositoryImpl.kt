package com.kek.redditfeed.main.feed.data.repository

import com.kek.redditfeed.main.feed.data.mapper.FeedResponseMapper
import com.kek.redditfeed.main.feed.data.net.FeedApi
import com.kek.redditfeed.main.feed.domain.model.FeedPostList
import com.kek.redditfeed.main.feed.domain.repository.FeedRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeedRepositoryImpl @Inject constructor(
  private val feedApi: FeedApi,
  private val feedResponseMapper: FeedResponseMapper
) : FeedRepository {

  override fun getTopPosts(count: Int, after: String): Single<FeedPostList> {
    return feedApi.getTopPosts(count, after)
      .map { response -> feedResponseMapper.map(response) }
  }
}