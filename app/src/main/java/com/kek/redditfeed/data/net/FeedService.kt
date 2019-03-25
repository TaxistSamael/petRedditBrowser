package com.kek.redditfeed.data.net

import com.kek.redditfeed.data.model.FeedResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FeedService {
    @GET("top/.json")
    fun getTopPosts(@Query("limit") limit: Int, @Query("after") after: String): Single<FeedResponse>
}