package com.kek.redditfeed.ui.full_screen.di

import androidx.fragment.app.FragmentActivity
import com.kek.redditfeed.base.di.scopes.PerFragment
import com.kek.redditfeed.ui.full_screen.FullScreenImageFragment
import com.kek.redditfeed.ui.main.di.MainActivityComponent
import com.kek.redditfeed.ui.main.di.mainActivityComponent
import dagger.Component

@PerFragment
@Component(dependencies = [MainActivityComponent::class])
interface FullScreenImageComponent {
  fun inject(fullScreenImageFragment: FullScreenImageFragment)
}

fun fullScreenImageComponent(activity: FragmentActivity) : FullScreenImageComponent {
  return DaggerFullScreenImageComponent.builder()
    .mainActivityComponent(mainActivityComponent(activity))
    .build()
}