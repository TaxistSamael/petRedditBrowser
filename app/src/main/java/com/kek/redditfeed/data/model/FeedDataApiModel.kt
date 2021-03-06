package com.kek.redditfeed.data.model

import com.google.gson.annotations.SerializedName

class FeedDataApiModel {
  @SerializedName("children")
  var children = emptyList<FeedChildApiModel>()
  @SerializedName("after")
  var after: String? = null
}
