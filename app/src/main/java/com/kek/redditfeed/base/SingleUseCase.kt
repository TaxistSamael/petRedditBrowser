package com.kek.redditfeed.base

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<T, in Params> {

  protected abstract fun buildSingleUseCase(params: Params): Single<T>

  fun executeWithSubscription(params: Params, onSuccess: (T) -> Unit, onFailure: (Throwable) -> Unit): Disposable {
    return buildSingleUseCase(params)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe({
        onSuccess(it)
      }, {
        onFailure(it)
      })
  }

  fun execute(params: Params): Single<T> {
    return buildSingleUseCase(params)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
  }
}