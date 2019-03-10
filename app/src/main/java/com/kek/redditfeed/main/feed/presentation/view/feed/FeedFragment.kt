package com.kek.redditfeed.main.feed.presentation.view.feed

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.kek.redditfeed.App.Companion.appComponent
import com.kek.redditfeed.R
import com.kek.redditfeed.base.presentation.BaseFragment
import com.kek.redditfeed.main.feed.di.DaggerFeedComponent
import com.kek.redditfeed.main.feed.presentation.model.FeedPostViewModel
import com.kek.redditfeed.main.feed.presentation.view.feed.list.FeedAdapter
import com.kek.redditfeed.main.feed.presentation.view.feed.list.FeedListItem
import com.kek.redditfeed.main.full_screen_image.FullScreenImageFragment
import com.kek.redditfeed.main.main.presentation.LIST_POSITION_NOT_INITIALIZED
import com.kek.redditfeed.main.main.presentation.clickedItemCurrentPosition
import com.kek.redditfeed.main.main.presentation.listOffset
import com.kek.redditfeed.main.main.presentation.listPosition
import com.kek.redditfeed.utils.doOnNextPage
import com.kek.redditfeed.utils.onClick
import kotlinx.android.synthetic.main.fragment_feed.*
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

//todo: test placeholder logic
//todo: make loader logic
//todo: сделать иконку плейсхолдера крупнее
private const val LIST_POSITION = "list_position"
private const val LIST_OFFSET = "list_offset"

class FeedFragment : BaseFragment(), FeedView {

  override val layoutRes = com.kek.redditfeed.R.layout.fragment_feed

  @Inject
  @InjectPresenter
  lateinit var presenter: FeedPresenter

  @Inject
  lateinit var feedAdapter: FeedAdapter

  @ProvidePresenter
  fun providePresenter(): FeedPresenter = presenter

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    handleBack()
  }

  private fun handleBack() {
    if (listPosition == LIST_POSITION_NOT_INITIALIZED) return
    feedRv.doOnLayout {
      if (feedRv == null) return@doOnLayout //todo: is it possible?
      feedRv.post {
        (feedRv.layoutManager as? LinearLayoutManager)?.scrollToPositionWithOffset(listPosition, listOffset)
      }
    }
  }

  private fun saveScrollPosition(outState: Bundle) {
    if (feedRv == null) return
    val positionIndex = (feedRv.layoutManager as? LinearLayoutManager)?.findFirstVisibleItemPosition() ?: return
    val startView = feedRv.getChildAt(0)
    val topView = if (startView == null) 0 else startView.top - feedRv.paddingTop
    outState.putInt(LIST_POSITION, positionIndex)
    outState.putInt(LIST_OFFSET, topView)
  }

  private fun saveScrollPositionGlobally() {
    if (feedRv == null) return
    val positionIndex = (feedRv.layoutManager as? LinearLayoutManager)?.findFirstVisibleItemPosition() ?: return
    val startView = feedRv.getChildAt(0)
    val topView = if (startView == null) 0 else startView.top - feedRv.paddingTop

    listPosition = positionIndex
    listOffset = topView
  }

  private fun restoreScrollPosition(savedInstanceState: Bundle?) {
    if (feedRv == null) return
    val positionIndex = savedInstanceState?.getInt(LIST_POSITION) ?: 0
    val topView = savedInstanceState?.getInt(LIST_OFFSET) ?: 0
    if (positionIndex == -1) return
    feedRv.post {
      (feedRv.layoutManager as? LinearLayoutManager)?.scrollToPositionWithOffset(positionIndex, topView)
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    setExitTransition()
    postponeEnterTransition()
    return super.onCreateView(inflater, container, savedInstanceState)
  }

  private fun setExitTransition() {
    exitTransition = TransitionInflater.from(context)
      .inflateTransition(R.transition.grid_exit_transition)
  }


  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    saveScrollPosition(outState)
    Log.d("droch", "saveInstanceState")
  }

  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    super.onViewStateRestored(savedInstanceState)
    restoreScrollPosition(savedInstanceState)
    Log.d("droch", "onViewStateRestored")
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

  //todo: handle placeholder logic
  override fun initListeners() {
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
    override fun onLoadCompleted(adapterPosition: Int, enterTransitionStarted: AtomicBoolean, position: Int) {
      if (clickedItemCurrentPosition != position) return
      if (enterTransitionStarted.getAndSet(true)) return
      startPostponedEnterTransition()
    }

    override fun onThumbnailClicked(post: FeedPostViewModel, transitioningView: View) {
      startPostponedEnterTransition()

      saveScrollPositionGlobally()

      fragmentManager?.apply {
        beginTransaction()
          .setReorderingAllowed(true)
          .addSharedElement(transitioningView, transitioningView.transitionName)
          .replace(R.id.fragment_container, FullScreenImageFragment.newInstance(post), "FullScreenImageFragment")
          .addToBackStack(null)
          .commit()
      }

    }
  }
}