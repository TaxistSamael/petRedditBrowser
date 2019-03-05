package com.kek.redditfeed.feed.data.net

import com.kek.redditfeed.feed.data.model.FeedResponse
import io.reactivex.Single

interface FeedApi {
  fun getTopPosts(count: Int, after: String = ""): Single<FeedResponse>
}