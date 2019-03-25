package com.kek.redditfeed.base.di

import com.kek.redditfeed.base.di.module.ApiModule
import com.kek.redditfeed.base.di.module.AppModule
import com.kek.redditfeed.base.di.module.RepositoryModule
import com.kek.redditfeed.base.di.module.WorkManagerModule
import com.kek.redditfeed.domain.repository.DownloadRepository
import com.kek.redditfeed.domain.repository.FeedRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class, RepositoryModule::class, WorkManagerModule::class])
interface AppComponent {
  fun feedRepository(): FeedRepository
  fun downloadRepository(): DownloadRepository
}