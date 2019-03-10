package com.kek.redditfeed.base.presentation

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable

open class BasePresenter<V : MvpView> : MvpPresenter<V>() {

  val disposables = CompositeDisposable()

  override fun onDestroy() {
    disposables.clear()
  }
}