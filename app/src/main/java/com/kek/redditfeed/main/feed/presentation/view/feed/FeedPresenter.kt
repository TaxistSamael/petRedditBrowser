package com.kek.redditfeed.main.feed.presentation.view.feed

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.kek.redditfeed.base.BasePresenter
import com.kek.redditfeed.base.Cursor
import com.kek.redditfeed.base.di.scopes.PerFragment
import com.kek.redditfeed.main.feed.domain.interactor.FeedPostsUseCase
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

@InjectViewState
@PerFragment
class FeedPresenter @Inject constructor(
  private val postsUseCase: FeedPostsUseCase
) : BasePresenter<FeedView>() {

  private val cursor = Cursor()
  private val getPostsParams: FeedPostsUseCase.Params
    get() = FeedPostsUseCase.Params.forTopPosts(cursor.next, cursor.offset)

  override fun onFirstViewAttach() {
    super.onFirstViewAttach()
    with(viewState) {
      initRecyclerView()
      initListeners()
      showProgress()
    }
    getPosts()
  }

  fun getPosts() {
    cursor.reset()
    getNextPage(isFirstPage = true)
  }

  fun appendPosts() {
    getNextPage(isFirstPage = false)
  }

  private fun getNextPage(isFirstPage: Boolean = true) {
    if (cursor.isLoading) return
    cursor.isLoading = true

    disposables += postsUseCase.execute(getPostsParams)
      .doFinally {
        viewState.hideProgress()
        cursor.isLoading = false
      }
      .subscribe({ model ->
        cursor.next = model.cursor
        when {
          isFirstPage -> viewState.onGetPostsSuccess(model.posts)
          else -> viewState.onAppendPostsSuccess(model.posts)
        }
      }, {
        Log.d("NIGGA", "$it") //todo: add error handling
      })
  }
}