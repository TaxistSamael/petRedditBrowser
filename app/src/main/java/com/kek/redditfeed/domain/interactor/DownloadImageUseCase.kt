package com.kek.redditfeed.domain.interactor

import com.kek.redditfeed.base.domain.CompletableUseCase
import com.kek.redditfeed.domain.repository.DownloadRepository
import io.reactivex.Completable
import javax.inject.Inject

class DownloadImageUseCase @Inject constructor(
  private val repository: DownloadRepository
): CompletableUseCase<DownloadImageUseCase.Params>() {

  override fun buildUseCaseObservable(params: Params): Completable {
    return repository.downloadImage(params.url)
  }

  class Params private constructor(val url: String) {
    companion object {
      fun forDownloadImage(url: String) : Params {
        return Params(url)
      }
    }
  }
}