package com.kek.redditfeed.data.net

import com.kek.redditfeed.data.model.FeedResponse
import io.reactivex.Single

interface FeedApi {
  fun getTopPosts(count: Int, after: String = ""): Single<FeedResponse>
}