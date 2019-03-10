package com.kek.redditfeed.main.full_screen_image.di

import com.kek.redditfeed.App.Companion.appComponent
import com.kek.redditfeed.base.di.AppComponent
import com.kek.redditfeed.base.di.scopes.PerFragment
import com.kek.redditfeed.main.full_screen_image.FullScreenImageFragment
import dagger.Component

@PerFragment
@Component(dependencies = [AppComponent::class])
interface FullScreenImageComponent {
  fun inject(fullScreenImageFragment: FullScreenImageFragment)
}

val fullScreenImageComponent: FullScreenImageComponent
  get() = DaggerFullScreenImageComponent.builder()
    .appComponent(appComponent)
    .build()