package com.kek.redditfeed.base.di.module

import com.kek.redditfeed.main.feed.data.net.FeedApi
import com.kek.redditfeed.main.feed.data.net.FeedApiImpl
import com.kek.redditfeed.main.feed.data.repository.FeedRepositoryImpl
import com.kek.redditfeed.main.feed.domain.repository.FeedRepository
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
}