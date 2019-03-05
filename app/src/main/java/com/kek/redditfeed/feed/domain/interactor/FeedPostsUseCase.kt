package com.kek.redditfeed.feed.domain.interactor

import com.kek.redditfeed.base.SingleUseCase
import com.kek.redditfeed.feed.domain.model.FeedPostList
import com.kek.redditfeed.feed.domain.repository.FeedRepository
import io.reactivex.Single
import javax.inject.Inject

class FeedPostsUseCase @Inject constructor(
  private val repository: FeedRepository
) : SingleUseCase<FeedPostList, FeedPostsUseCase.Params>() {

  override fun buildSingleUseCase(params: Params): Single<FeedPostList> {
    return repository.getTopPosts(params.count, params.cursor)
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