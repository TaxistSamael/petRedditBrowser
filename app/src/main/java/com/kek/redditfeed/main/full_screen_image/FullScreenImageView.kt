package com.kek.redditfeed.main.full_screen_image

import com.arellomobile.mvp.MvpView
import com.kek.redditfeed.main.feed.presentation.model.FeedPostViewModel

interface FullScreenImageView : MvpView {
  fun showImage(post: FeedPostViewModel)
  fun initListeners()
  fun setTitle(title: String)
}