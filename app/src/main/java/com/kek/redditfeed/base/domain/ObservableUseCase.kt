package com.kek.redditfeed.base.domain

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class ObservableUseCase<T, in Params> {

  protected abstract fun buildObservableUseCase(params: Params): Observable<T>

  fun executeWithSubscription(params: Params, onSuccess: (T) -> Unit, onFailure: (Throwable) -> Unit): Disposable {
    return buildObservableUseCase(params)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe({
        onSuccess(it)
      }, {
        onFailure(it)
      })
  }

  fun execute(params: Params): Observable<T> {
    return buildObservableUseCase(params)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
  }
}