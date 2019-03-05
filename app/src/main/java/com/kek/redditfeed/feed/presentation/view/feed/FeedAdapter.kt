package com.kek.redditfeed.feed.presentation.view.feed

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kek.redditfeed.feed.presentation.model.FeedPostViewModel

class FeedAdapter : RecyclerView.Adapter<FeedItem>() {

  private val dataSet = mutableListOf<FeedPostViewModel>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedItem = FeedItem(parent)

  override fun getItemCount(): Int = dataSet.size

  override fun onBindViewHolder(holder: FeedItem, position: Int) {
    holder.bind(dataSet[position])
  }

  fun swapPosts(posts: List<FeedPostViewModel>) {
    dataSet.clear()
    dataSet.addAll(posts)
    notifyDataSetChanged()
  }

  fun appendPosts(posts: List<FeedPostViewModel>) {
//    notifyItemRangeChanged()
    //todo:
  }
}