package com.kek.redditfeed.domain.repository

import io.reactivex.Completable

interface DownloadRepository {
  fun downloadImage(url: String): Completable
}