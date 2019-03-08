package com.kek.redditfeed.main.main.di

import com.kek.redditfeed.base.di.AppComponent
import com.kek.redditfeed.base.di.scopes.PerActivity
import com.kek.redditfeed.main.main.presentation.MainActivity
import dagger.Component

@PerActivity
@Component(dependencies = [AppComponent::class])
interface MainActivityComponent {
  fun inject(mainActivity: MainActivity)
}
