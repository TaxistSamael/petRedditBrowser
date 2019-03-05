package com.kek.redditfeed.feed.presentation.mapper

import com.kek.redditfeed.base.Mapper
import com.kek.redditfeed.feed.domain.model.FeedPostList
import com.kek.redditfeed.feed.presentation.model.FeedPostListViewModel
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