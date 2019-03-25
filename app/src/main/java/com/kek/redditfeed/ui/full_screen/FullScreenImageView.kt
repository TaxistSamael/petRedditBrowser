package com.kek.redditfeed.ui.full_screen

import androidx.annotation.StringRes
import com.arellomobile.mvp.MvpView
import com.kek.redditfeed.presentation.model.FeedPostViewModel

interface FullScreenImageView : MvpView {
  fun showImage(post: FeedPostViewModel)
  fun initListeners()
  fun setTitle(title: String)
  fun showToast(@StringRes textId: Int)
}