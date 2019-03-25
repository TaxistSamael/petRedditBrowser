package com.kek.redditfeed.base.di.module

import androidx.work.WorkManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class WorkManagerModule {

  @Singleton
  @Provides
  fun provideWorkManager(): WorkManager {
    return WorkManager.getInstance()
  }
}