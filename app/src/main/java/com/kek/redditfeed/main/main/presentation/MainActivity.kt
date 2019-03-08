package com.kek.redditfeed.main.main.presentation

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.kek.redditfeed.App.Companion.appComponent
import com.kek.redditfeed.R
import com.kek.redditfeed.base.BaseActivity
import com.kek.redditfeed.main.feed.presentation.view.feed.FeedFragment
import com.kek.redditfeed.main.main.di.DaggerMainActivityComponent
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

  override val layoutRes = R.layout.activity_main

  @Inject
  @InjectPresenter
  lateinit var presenter: MainPresenter

  override fun injectDaggerDependency() {
    DaggerMainActivityComponent.builder()
      .appComponent(appComponent)
      .build()
      .inject(this)
  }

  override fun setFeedFragment() {
    supportFragmentManager.beginTransaction()
      .replace(R.id.root, FeedFragment())
      .commit()
  }

  @ProvidePresenter
  fun providePresenter(): MainPresenter {
    return presenter
  }
}