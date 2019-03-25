package com.kek.redditfeed.domain.repository

import com.kek.redditfeed.domain.model.FeedPostList
import io.reactivex.Single

interface FeedRepository {
  fun getTopPosts(count: Int, after: String = ""): Single<FeedPostList>
}