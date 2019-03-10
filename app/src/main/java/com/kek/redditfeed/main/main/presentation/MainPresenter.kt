package com.kek.redditfeed.main.main.presentation

import com.arellomobile.mvp.InjectViewState
import com.kek.redditfeed.base.presentation.BasePresenter
import com.kek.redditfeed.base.di.scopes.PerActivity
import javax.inject.Inject

@InjectViewState
@PerActivity
class MainPresenter @Inject constructor() : BasePresenter<MainView>() {

  override fun onFirstViewAttach() {
    super.onFirstViewAttach()
    viewState.setFeedFragment()
  }
}