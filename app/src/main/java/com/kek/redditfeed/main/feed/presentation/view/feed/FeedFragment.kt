package com.kek.redditfeed.main.feed.presentation.view.feed

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.kek.redditfeed.App.Companion.appComponent
import com.kek.redditfeed.base.BaseFragment
import com.kek.redditfeed.main.feed.di.DaggerFeedComponent
import com.kek.redditfeed.main.feed.presentation.model.FeedPostViewModel
import com.kek.redditfeed.main.feed.presentation.view.feed.list.FeedAdapter
import com.kek.redditfeed.main.feed.presentation.view.feed.list.FeedListItem
import com.kek.redditfeed.utils.doOnNextPage
import com.kek.redditfeed.utils.onClick
import kotlinx.android.synthetic.main.fragment_feed.*
import javax.inject.Inject

//todo: test placeholder logic
//todo: test main logic
//todo: make loader logic
//todo: do new fragment logic
//todo: fix bug with swipe refresh and saving cursor
//todo: сделать иконку плейсхолдера крупнее
//todo: remove view binding if no need in it
class FeedFragment : BaseFragment(), FeedView {

  override val layoutRes = com.kek.redditfeed.R.layout.fragment_feed

  @Inject
  @InjectPresenter
  lateinit var presenter: FeedPresenter

  @Inject
  lateinit var feedAdapter: FeedAdapter

  @ProvidePresenter
  fun providePresenter(): FeedPresenter = presenter

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    saveScrollPosition(outState)
  }

  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    super.onViewStateRestored(savedInstanceState)
    restoreScrollPosition(savedInstanceState)
  }

  override fun initRecyclerView() {
    feedAdapter.thumbnailClickCallback = thumbnailClickCallback

    with(feedRv) {
      layoutManager = LinearLayoutManager(context)
      adapter = feedAdapter
      setHasFixedSize(true)
      doOnNextPage({ presenter.appendPosts() })
    }
  }

  override fun initListeners() {
    //todo: set action with debounce
    feedPlaceholderTv.onClick { }
    feedSwipeRefresh.setOnRefreshListener { presenter.getPosts() }
  }

  //todo: show/hide placeholder logic
  override fun onGetPostsSuccess(posts: List<FeedPostViewModel>) {
    feedAdapter.swapData(posts)
  }

  override fun onGetPostsFailure(message: String) {
    //todo:
  }

  override fun onAppendPostsSuccess(posts: List<FeedPostViewModel>) {
    feedAdapter.appendData(posts)
  }

  override fun onAppendPostsFailure(message: String) {
    //todo:
  }

  override fun hideProgress() {
    feedSwipeRefresh.isRefreshing = false
  }

  override fun showProgress() {
    feedSwipeRefresh.isRefreshing = true
  }

  override fun injectDaggerDependency() {
    DaggerFeedComponent.builder()
      .appComponent(appComponent)
      .build()
      .inject(this)
  }

  private val thumbnailClickCallback = object : FeedListItem.Callback {
    override fun onThumbnailClicked(post: FeedPostViewModel) {
      Toast.makeText(this@FeedFragment.context, "OLOLO", Toast.LENGTH_SHORT).show()
      //todo
    }
  }

  private fun saveScrollPosition(outState: Bundle) {
    val positionIndex = (feedRv.layoutManager as? LinearLayoutManager)?.findFirstVisibleItemPosition() ?: return
    val startView = feedRv.getChildAt(0)
    val topView = if (startView == null) 0 else startView.top - feedRv.paddingTop
    outState.putInt("kek", positionIndex)
    outState.putInt("lol", topView)
  }

  private fun restoreScrollPosition(savedInstanceState: Bundle?) {
    val positionIndex = savedInstanceState?.getInt("kek") ?: 0
    val topView = savedInstanceState?.getInt("lol") ?: 0
    if (positionIndex == -1) return
    feedRv.post {
      (feedRv.layoutManager as? LinearLayoutManager)?.scrollToPositionWithOffset(positionIndex, topView)
    }
  }
}