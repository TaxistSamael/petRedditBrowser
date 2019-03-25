package com.kek.redditfeed.ui.main.di

import androidx.fragment.app.FragmentActivity
import com.kek.redditfeed.App
import com.kek.redditfeed.base.di.AppComponent
import com.kek.redditfeed.base.di.module.PermissionsRepositoryModule
import com.kek.redditfeed.base.di.scopes.PerActivity
import com.kek.redditfeed.domain.repository.DownloadRepository
import com.kek.redditfeed.domain.repository.FeedRepository
import com.kek.redditfeed.domain.repository.PermissionsRepository
import com.kek.redditfeed.ui.main.MainActivity
import dagger.Component

@PerActivity
@Component(dependencies = [AppComponent::class], modules = [PermissionsRepositoryModule::class])
interface MainActivityComponent {
  fun inject(mainActivity: MainActivity)

  fun permissionsRepository(): PermissionsRepository
  fun feedRepository(): FeedRepository
  fun downloadRepository(): DownloadRepository
}

fun mainActivityComponent(activity: FragmentActivity): MainActivityComponent {
  return DaggerMainActivityComponent.builder()
    .appComponent(App.appComponent)
    .permissionsRepositoryModule(PermissionsRepositoryModule(activity))
    .build()
}
