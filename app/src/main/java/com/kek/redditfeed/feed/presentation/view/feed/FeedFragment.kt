package com.kek.redditfeed.feed.presentation.view.feed

import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.kek.redditfeed.R
import com.kek.redditfeed.base.BaseFragment
import com.kek.redditfeed.feed.presentation.model.FeedPostListViewModel
import com.kek.redditfeed.utils.doOnNextPage
import com.kek.redditfeed.utils.onClick
import kotlinx.android.synthetic.main.fragment_feed.*

//todo: test placeholder logic
//todo: test main logic
class FeedFragment : BaseFragment(), FeedView {

  override val layoutRes: Int = R.layout.fragment_feed

  @InjectPresenter
  lateinit var presenter: FeedPresenter

  override fun initRecyclerView() {
    with(feedRv) {
      layoutManager = LinearLayoutManager(context)
      adapter = FeedAdapter() //todo: think it's bad idea
      doOnNextPage({ presenter.getPosts() })
    }
  }

  override fun initListeners() {
    feedPlaceholderTv.onClick { } //todo
    //todo: set action with debounce
  }

  override fun onGetPostsSuccess(posts: FeedPostListViewModel) {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun onGetPostsFailure(message: String) {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun onAppendPostsSuccess(posts: FeedPostListViewModel) {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun onAppendPostsFailure(message: String) {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun injectDaggerDependency() {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}