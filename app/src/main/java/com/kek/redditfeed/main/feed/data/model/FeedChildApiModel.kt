package com.kek.redditfeed.main.feed.data.model

import com.google.gson.annotations.SerializedName

class FeedChildApiModel {
  @SerializedName("data")
  var data: FeedPostApiModel = FeedPostApiModel()
}
