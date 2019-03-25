package com.kek.redditfeed.ui.main

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.kek.redditfeed.R
import com.kek.redditfeed.base.presentation.BaseActivity
import com.kek.redditfeed.ui.feed.FeedFragment
import com.kek.redditfeed.ui.main.di.mainActivityComponent
import javax.inject.Inject

/**
 * Holds the current image position to be shared between fragments. This
 * position updates when list item is clicked.
 */
var clickedItemCurrentPosition: Int = 0
const val LIST_POSITION_NOT_INITIALIZED = -1
var listPosition = LIST_POSITION_NOT_INITIALIZED
var listOffset = LIST_POSITION_NOT_INITIALIZED

private val KEY_CURRENT_POSITION = "com.google.samples.gridtopager.key.clickedItemCurrentPosition"

//todo: запилить dependencies.gradle как в одной маленькой, но гордой социальной сети
class MainActivity : BaseActivity(), MainView {

  override val layoutRes = R.layout.activity_main

  @Inject
  @InjectPresenter
  lateinit var presenter: MainPresenter

  override fun injectDependency() {
    mainActivityComponent(this).inject(this)
  }

  override fun setFeedFragment() {
    supportFragmentManager.beginTransaction()
      .add(R.id.fragment_container, FeedFragment(), "FeedFragment")
      .commit()
  }

  @ProvidePresenter
  fun providePresenter(): MainPresenter {
    return presenter
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (savedInstanceState != null) {
      clickedItemCurrentPosition = savedInstanceState.getInt(KEY_CURRENT_POSITION, 0)
      listPosition = savedInstanceState.getInt(cheburek, 0)
      listOffset = savedInstanceState.getInt(orbidol, 0)
      return
    }
  }

  val cheburek = "CHEBUREK"
  val orbidol = "ORBIDOL"

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    outState.putInt(
      KEY_CURRENT_POSITION,
      clickedItemCurrentPosition
    )
    outState.putInt(cheburek, listPosition)
    outState.putInt(orbidol, listOffset)

  }
}