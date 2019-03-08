package com.kek.redditfeed.main.feed.di

import com.kek.redditfeed.base.di.AppComponent
import com.kek.redditfeed.base.di.scopes.PerFragment
import com.kek.redditfeed.main.feed.presentation.view.feed.list.FeedAdapter
import com.kek.redditfeed.main.feed.presentation.view.feed.FeedFragment
import dagger.Component

@PerFragment
@Component(dependencies = [AppComponent::class])
interface FeedComponent {
  fun feedAdapter(): FeedAdapter
  fun inject(feedFragment: FeedFragment)
}