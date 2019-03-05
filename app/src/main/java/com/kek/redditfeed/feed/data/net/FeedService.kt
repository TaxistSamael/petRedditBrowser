package com.kek.redditfeed.feed.data.net

import com.kek.redditfeed.feed.data.model.FeedResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FeedService {
    @GET("top/.json")
    fun getTopPosts(@Query("count") count: Int, @Query("after") after: String): Single<FeedResponse>
}