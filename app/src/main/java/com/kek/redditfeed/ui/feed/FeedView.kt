package com.kek.redditfeed.ui.feed

import com.arellomobile.mvp.MvpView
import com.kek.redditfeed.presentation.model.FeedPostViewModel

interface FeedView : MvpView {
  fun initRecyclerView()
  fun initListeners()

  fun onGetPostsSuccess(posts: List<FeedPostViewModel>)
  fun onGetPostsFailure(message: String)

  fun onAppendPostsSuccess(posts: List<FeedPostViewModel>)
  fun onAppendPostsFailure(message: String)

  fun hideProgress()
  fun showProgress()
}