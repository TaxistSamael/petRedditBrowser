package com.kek.redditfeed.ui.feed.di

import androidx.fragment.app.FragmentActivity
import com.kek.redditfeed.base.di.scopes.PerFragment
import com.kek.redditfeed.ui.feed.FeedFragment
import com.kek.redditfeed.ui.feed.list.FeedAdapter
import com.kek.redditfeed.ui.main.di.MainActivityComponent
import com.kek.redditfeed.ui.main.di.mainActivityComponent
import dagger.Component

@PerFragment
@Component(dependencies = [MainActivityComponent::class])
interface FeedComponent {
  fun feedAdapter(): FeedAdapter
  fun inject(feedFragment: FeedFragment)
}

fun feedComponent(activity: FragmentActivity): FeedComponent {
  return DaggerFeedComponent.builder()
    .mainActivityComponent(mainActivityComponent(activity))
    .build()
}