package com.kek.redditfeed.feed.presentation.view.feed

import com.arellomobile.mvp.InjectViewState
import com.kek.redditfeed.base.BasePresenter

@InjectViewState
class FeedPresenter: BasePresenter<FeedView>() {

  override fun onFirstViewAttach() {
    super.onFirstViewAttach()
    super.onFirstViewAttach()
    viewState.initRecyclerView()
    viewState.initListeners()
    getPosts()
  }

  fun getPosts() {
    //todo:
  }

  override fun onDestroy() {
    super.onDestroy()
  }
}