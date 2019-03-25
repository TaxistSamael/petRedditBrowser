package com.kek.redditfeed.domain.mapper

import com.kek.redditfeed.base.domain.Mapper
import com.kek.redditfeed.domain.model.FeedPostList
import com.kek.redditfeed.presentation.model.FeedPostListViewModel
import javax.inject.Inject

class FeedPostListViewModelMapper @Inject constructor(
  private val feedPostMapper: FeedPostViewModelMapper
) : Mapper<FeedPostList, FeedPostListViewModel>() {
  override fun reverse(to: FeedPostListViewModel): FeedPostList {
    throw NotImplementedError()
  }

  override fun map(from: FeedPostList): FeedPostListViewModel {
    with(from) {
      return FeedPostListViewModel(
        cursor = cursor,
        posts = posts.map { feedPost -> feedPostMapper.map(feedPost) }
      )
    }
  }
}