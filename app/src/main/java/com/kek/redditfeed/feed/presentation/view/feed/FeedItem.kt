package com.kek.redditfeed.feed.presentation.view.feed

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kek.redditfeed.R
import com.kek.redditfeed.feed.presentation.model.FeedPostViewModel
import com.kek.redditfeed.utils.inflate

class FeedItem(parent: ViewGroup) : RecyclerView.ViewHolder(
    parent.inflate(R.layout.list_item_feed)) {

  fun bind(item: FeedPostViewModel) {
    with(itemView) {
      //todo: some binding logic
    }
  }
}