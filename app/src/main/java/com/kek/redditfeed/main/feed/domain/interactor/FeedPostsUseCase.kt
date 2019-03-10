package com.kek.redditfeed.main.feed.domain.interactor

import com.kek.redditfeed.base.domain.SingleUseCase
import com.kek.redditfeed.main.feed.domain.repository.FeedRepository
import com.kek.redditfeed.main.feed.domain.mapper.FeedPostListViewModelMapper
import com.kek.redditfeed.main.feed.presentation.model.FeedPostListViewModel
import io.reactivex.Single
import javax.inject.Inject

class FeedPostsUseCase @Inject constructor(
    private val repository: FeedRepository,
    private val mapper: FeedPostListViewModelMapper
) : SingleUseCase<FeedPostListViewModel, FeedPostsUseCase.Params>() {

  override fun buildSingleUseCase(params: Params): Single<FeedPostListViewModel> {
    return repository.getTopPosts(params.count, params.cursor)
        .map { mapper.map(it) }
  }

  class Params private constructor(
      val cursor: String,
      val count: Int
  ) {
    companion object {
      fun forTopPosts(cursor: String, count: Int): Params {
        return Params(cursor, count)
      }
    }
  }
}