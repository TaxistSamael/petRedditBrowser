package com.kek.redditfeed.base.di.module

import com.kek.redditfeed.data.net.FeedApi
import com.kek.redditfeed.data.net.FeedApiImpl
import com.kek.redditfeed.data.repository.DownloadRepositoryImpl
import com.kek.redditfeed.data.repository.FeedRepositoryImpl
import com.kek.redditfeed.domain.repository.DownloadRepository
import com.kek.redditfeed.domain.repository.FeedRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {
  @Binds
  @Singleton
  abstract fun feedRepository(feedRepository: FeedRepositoryImpl): FeedRepository

  @Binds
  @Singleton
  abstract fun feedApi(feedApiImpl: FeedApiImpl): FeedApi

  @Binds
  @Singleton
  abstract fun downloadRepository(downloadRepository: DownloadRepositoryImpl): DownloadRepository
}