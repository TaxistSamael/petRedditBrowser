package com.kek.redditfeed.main.feed.presentation.view.feed.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kek.redditfeed.base.di.scopes.PerFragment
import com.kek.redditfeed.main.feed.presentation.model.FeedPostViewModel
import javax.inject.Inject

//todo: причесать appendData
@PerFragment
class FeedAdapter @Inject constructor() : RecyclerView.Adapter<FeedListItem>() {

  var thumbnailClickCallback: FeedListItem.Callback? = null

  private val dataSet = mutableListOf<FeedPostViewModel>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedListItem =
    FeedListItem(parent)

  override fun getItemCount(): Int = dataSet.size

  override fun onBindViewHolder(holder: FeedListItem, position: Int) {
    holder.bind(dataSet[position])
  }

  fun swapData(posts: List<FeedPostViewModel>) {
    dataSet.clear()
    dataSet.addAll(posts)
    notifyDataSetChanged()
  }

  fun appendData(posts: List<FeedPostViewModel>) {
    val newList: MutableList<FeedPostViewModel> = ArrayList(dataSet)
    newList.addAll(posts)
    val diffCallback = PostsDiffCallback(dataSet, newList)
    val diffResult = DiffUtil.calculateDiff(diffCallback)
    dataSet.clear()
    dataSet.addAll(newList)
    diffResult.dispatchUpdatesTo(this)
  }
}