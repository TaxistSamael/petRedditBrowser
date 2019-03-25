package com.kek.redditfeed.base.di.module

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import com.kek.redditfeed.base.di.scopes.PerActivity
import com.kek.redditfeed.data.repository.PermissionsRepositoryImpl
import com.kek.redditfeed.domain.repository.PermissionsRepository
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.Module
import dagger.Provides

@Module
class PermissionsRepositoryModule(private val activity: FragmentActivity) {

  @Provides
  @PerActivity
  fun provideRxPermissions(): RxPermissions {
    return RxPermissions(activity)
  }

  @Provides
  @PerActivity
  fun provideActivity(): Activity = activity

  @Provides
  @PerActivity
  fun providePermissionsRepository(repo: PermissionsRepositoryImpl): PermissionsRepository = repo
}