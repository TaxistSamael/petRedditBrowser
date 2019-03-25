package com.kek.redditfeed.ui.main

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface MainView : MvpView {
  @StateStrategyType(OneExecutionStateStrategy::class)
  fun setFeedFragment()
}