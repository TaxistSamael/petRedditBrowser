package com.kek.redditfeed.feed.data.model

import com.google.gson.annotations.SerializedName

//todo: make thumbnail height/width logic somehow
class FeedPostApiModel(
  @SerializedName("id") val id: String = "",
  @SerializedName("title") val title: String = "",
  @SerializedName("author") val author: String = "",
  @SerializedName("created_utc") val createdUtc: Long = 0L,
  @SerializedName("num_comments") val numComments: Int = 0,
  @SerializedName("url") val url: String = ""
)
