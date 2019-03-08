package com.kek.redditfeed.main.main.presentation

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface MainView : MvpView {
  @StateStrategyType(OneExecutionStateStrategy::class)
  fun setFeedFragment()
}