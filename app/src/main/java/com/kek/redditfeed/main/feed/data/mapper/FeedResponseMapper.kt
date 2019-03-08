package com.kek.redditfeed.main.feed.data.mapper

import com.kek.redditfeed.base.Mapper
import com.kek.redditfeed.main.feed.data.model.FeedResponse
import com.kek.redditfeed.main.feed.domain.model.FeedPostList
import javax.inject.Inject

class FeedResponseMapper @Inject constructor(
  private val feedPostMapper: FeedPostApiModelMapper
) : Mapper<FeedResponse, FeedPostList>() {

  override fun reverse(to: FeedPostList) = throw NotImplementedError()

  override fun map(from: FeedResponse): FeedPostList {
    return FeedPostList(
      cursor = from.data?.after ?: "",
      posts = from.data?.children?.map { feedPostMapper.map(it.data) } ?: emptyList()
    )
  }
}