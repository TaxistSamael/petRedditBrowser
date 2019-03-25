package com.kek.redditfeed.ui.full_screen

import com.arellomobile.mvp.InjectViewState
import com.kek.redditfeed.R
import com.kek.redditfeed.base.di.scopes.PerFragment
import com.kek.redditfeed.base.presentation.BasePresenter
import com.kek.redditfeed.data.permission.Permission
import com.kek.redditfeed.data.permission.PermissionStatus.GRANTED
import com.kek.redditfeed.domain.interactor.DownloadImageUseCase
import com.kek.redditfeed.domain.interactor.RequestPermissionsUseCase
import com.kek.redditfeed.presentation.model.FeedPostViewModel
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

//todo: how to observe work manager's result

@PerFragment
@InjectViewState
class FullScreenImagePresenter @Inject constructor(
  private val downloadImageUseCase: DownloadImageUseCase,
  private val permissionsUseCase: RequestPermissionsUseCase
) : BasePresenter<FullScreenImageView>() {

  var post: FeedPostViewModel? = null

  private val downloadParams: DownloadImageUseCase.Params
    get() {
      //todo: make mime type method for images
      val url = if (post?.url?.contains("jpeg") == true) post?.url else post?.thumbnail
      return DownloadImageUseCase.Params.forDownloadImage(url ?: "")
    }

  private val writeStorageParams: RequestPermissionsUseCase.Params
    get() = RequestPermissionsUseCase.Params(arrayOf(Permission.WRITE_STORAGE))

  override fun onFirstViewAttach() {
    super.onFirstViewAttach()
    with(viewState) {
      initListeners()
      setTitle(post?.title ?: "")
      showImage(post ?: return)
    }
  }

  fun downloadImage() {
    disposables += permissionsUseCase.execute(writeStorageParams)
      .subscribe({ (_, status) ->
        when (status) {
          GRANTED -> doDownloadImage()
          else -> viewState.showToast(R.string.error_permission_not_granted)
        }
      }, {
        viewState.showToast(R.string.something_went_wrong)
      })
  }

  private fun doDownloadImage() {
    disposables += downloadImageUseCase.execute(downloadParams)
      .subscribe({
        viewState.showToast(R.string.loading_is_started)
      }, {
        viewState.showToast(R.string.something_went_wrong)
      })
  }

}