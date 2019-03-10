package com.kek.redditfeed.main.full_screen_image

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.kek.redditfeed.base.di.scopes.PerFragment
import com.kek.redditfeed.base.presentation.BasePresenter
import com.kek.redditfeed.main.feed.presentation.model.FeedPostViewModel
import javax.inject.Inject

@PerFragment
@InjectViewState
class FullScreenImagePresenter @Inject constructor(): BasePresenter<FullScreenImageView>() {

  var post: FeedPostViewModel? = null

  override fun onFirstViewAttach() {
    super.onFirstViewAttach()
    Log.d("EBAL_VOEVAL", "post: $post")
    with(viewState) {
      initListeners()
      setTitle(post?.title ?: "")
      showImage(post ?: return)
    }
  }
}