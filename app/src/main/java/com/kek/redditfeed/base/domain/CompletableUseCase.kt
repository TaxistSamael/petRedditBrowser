package com.kek.redditfeed.base.domain

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class CompletableUseCase<in Params> {

  protected abstract fun buildUseCaseObservable(params: Params): Completable

  fun executeWithSubscription(params: Params, onSuccess: () -> Unit, onFailure: (Throwable) -> Unit): Disposable {
    return buildUseCaseObservable(params)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe({
        onSuccess()
      }, {
        onFailure(it)
      })
  }

  fun execute(params: Params): Completable {
    return buildUseCaseObservable(params)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
  }

}