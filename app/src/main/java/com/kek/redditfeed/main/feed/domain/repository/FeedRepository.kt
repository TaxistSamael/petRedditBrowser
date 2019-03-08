package com.kek.redditfeed.main.feed.domain.repository

import com.kek.redditfeed.main.feed.domain.model.FeedPostList
import io.reactivex.Single

interface FeedRepository {
  fun getTopPosts(count: Int, after: String = ""): Single<FeedPostList>
}