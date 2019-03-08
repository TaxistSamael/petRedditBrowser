package com.kek.redditfeed.main.feed.presentation.view.feed.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kek.redditfeed.R
import com.kek.redditfeed.main.feed.presentation.model.FeedPostViewModel
import com.kek.redditfeed.utils.inflate
import com.kek.redditfeed.utils.setImageUri
import kotlinx.android.synthetic.main.list_item_feed.view.*

class FeedListItem(parent: ViewGroup) : RecyclerView.ViewHolder(
    parent.inflate(R.layout.list_item_feed)) {

  fun bind(post: FeedPostViewModel) {
    with(itemView) {
      itemFeedIv.setImageUri(post.thumbnail)
      itemFeedTvComments.text = post.numComments.toString()
      itemFeedTvPostedBy.text = post.getAuthorTimeString(context)
      itemFeedTvTitle.text = post.title
    }
  }

  interface Callback {
    fun onThumbnailClicked(post: FeedPostViewModel)
  }
}