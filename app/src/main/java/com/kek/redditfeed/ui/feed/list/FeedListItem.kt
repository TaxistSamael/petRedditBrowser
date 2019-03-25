package com.kek.redditfeed.ui.feed.list

import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy.ALL
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.kek.redditfeed.R
import com.kek.redditfeed.presentation.model.FeedPostViewModel
import com.kek.redditfeed.ui.main.clickedItemCurrentPosition
import com.kek.redditfeed.utils.inflate
import com.kek.redditfeed.utils.onClick
import kotlinx.android.synthetic.main.list_item_2.view.*
import java.util.concurrent.atomic.AtomicBoolean

class FeedListItem(
  parent: ViewGroup,
  private val imageClickCallback: Callback?
) : RecyclerView.ViewHolder(parent.inflate(R.layout.list_item_2)) {

  private val enterTransitionStarted = AtomicBoolean()

  fun bind(post: FeedPostViewModel) {
    with(itemView) {
      card_image.transitionName = post.id
      Glide.with(card_image)
        .load(post.thumbnail)
        .listener(loadCompletedListener)
        .diskCacheStrategy(ALL)
        .apply(
          RequestOptions
            .placeholderOf(R.drawable.ic_placeholder)
            .error(R.drawable.ic_placeholder)
        )
        .into(card_image)

      card_image.onClick {
        clickedItemCurrentPosition = layoutPosition
        imageClickCallback?.onThumbnailClicked(post, card_image)
      }
    }
  }

  private val loadCompletedListener = object : RequestListener<Drawable> {
    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
      imageClickCallback?.onLoadCompleted(adapterPosition, enterTransitionStarted, layoutPosition)
      return false
    }

    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
      imageClickCallback?.onLoadCompleted(adapterPosition, enterTransitionStarted, layoutPosition)
      return false
    }
  }

  interface Callback {
    fun onThumbnailClicked(post: FeedPostViewModel, transitioningView: View)
    fun onLoadCompleted(adapterPosition: Int, enterTransitionStarted: AtomicBoolean, position: Int)
  }
}