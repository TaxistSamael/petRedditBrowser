package com.kek.redditfeed.main.feed.presentation.view.feed.list

import androidx.recyclerview.widget.DiffUtil
import com.kek.redditfeed.main.feed.presentation.model.FeedPostViewModel

//todo: make async implementation
class PostsDiffCallback(
  private val oldList: MutableList<FeedPostViewModel>,
  private val newList: MutableList<FeedPostViewModel>
) : DiffUtil.Callback() {

  override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    return oldList[oldItemPosition].id == newList[newItemPosition].id
  }

  override fun getOldListSize(): Int = oldList.size

  override fun getNewListSize(): Int = newList.size

  override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    return oldList[oldItemPosition].numComments == newList[newItemPosition].numComments
  }
}