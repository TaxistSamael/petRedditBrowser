package com.kek.redditfeed.feed.data.model

import com.google.gson.annotations.SerializedName

class FeedChildApiModel {
  @SerializedName("data")
  var data: FeedPostApiModel = FeedPostApiModel()
}
