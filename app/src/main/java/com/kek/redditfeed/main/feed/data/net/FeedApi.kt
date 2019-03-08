package com.kek.redditfeed.main.feed.data.net

import com.kek.redditfeed.main.feed.data.model.FeedResponse
import io.reactivex.Single

interface FeedApi {
  fun getTopPosts(count: Int, after: String = ""): Single<FeedResponse>
}