package com.kek.redditfeed.feed.presentation.view.feed

import com.arellomobile.mvp.MvpView
import com.kek.redditfeed.feed.presentation.model.FeedPostListViewModel

interface FeedView : MvpView {
  fun initRecyclerView()
  fun initListeners()

  fun onGetPostsSuccess(posts: FeedPostListViewModel)
  fun onGetPostsFailure(message: String)

  fun onAppendPostsSuccess(posts: FeedPostListViewModel)
  fun onAppendPostsFailure(message: String)
}