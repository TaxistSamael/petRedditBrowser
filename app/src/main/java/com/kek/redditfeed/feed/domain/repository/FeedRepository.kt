package com.kek.redditfeed.feed.domain.repository

import com.kek.redditfeed.feed.domain.model.FeedPostList
import io.reactivex.Single

interface FeedRepository {
  fun getTopPosts(count: Int, after: String = ""): Single<FeedPostList>
}