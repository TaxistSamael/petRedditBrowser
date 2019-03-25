package com.kek.redditfeed.data.model

import com.google.gson.annotations.SerializedName

class FeedResponse {
  @SerializedName("data")
  var data: FeedDataApiModel? = null
}
