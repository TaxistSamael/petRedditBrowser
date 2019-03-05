package com.kek.redditfeed.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable

open class BasePresenter<V : MvpView> : MvpPresenter<V>() {

  val compositeDisposable = CompositeDisposable()

  override fun onDestroy() {
    compositeDisposable.dispose()
  }
}