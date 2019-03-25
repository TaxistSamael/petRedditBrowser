package com.kek.redditfeed.ui.full_screen

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.kek.redditfeed.base.presentation.BaseFragment
import com.kek.redditfeed.presentation.model.FeedPostViewModel
import com.kek.redditfeed.ui.full_screen.di.fullScreenImageComponent
import com.kek.redditfeed.utils.onClick
import com.kek.redditfeed.utils.toast
import kotlinx.android.synthetic.main.fragment_full_screen_image.*
import javax.inject.Inject

const val FULL_SCREEN_ARGUMENT = "FULL_SCREEN_ARGUMENT"

//todo: прочитать про cach strategy
//todo: think about image utils
//todo: make a pallette color for full screen: https://android.jlelse.eu/dynamic-colors-with-glide-library-and-android-palette-5be407049d97
class FullScreenImageFragment : BaseFragment(), FullScreenImageView {

  companion object {
    fun newInstance(post: FeedPostViewModel): FullScreenImageFragment {
      return FullScreenImageFragment().apply {
        arguments = bundleOf(FULL_SCREEN_ARGUMENT to post)
      }
    }
  }

  override val layoutRes = com.kek.redditfeed.R.layout.fragment_full_screen_image

  @Inject
  @InjectPresenter
  lateinit var presenter: FullScreenImagePresenter

  @ProvidePresenter
  fun providePresenter(): FullScreenImagePresenter {
    presenter.post = arguments?.getParcelable(FULL_SCREEN_ARGUMENT)
    return presenter
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    prepareSharedElementTransition()
    return super.onCreateView(inflater, container, savedInstanceState)
  }

  private fun prepareSharedElementTransition() {
    val transitionInflater = TransitionInflater.from(context)
    sharedElementEnterTransition =
      transitionInflater.inflateTransition(com.kek.redditfeed.R.transition.image_shared_element_transition)
  }

  override fun showImage(post: FeedPostViewModel) {
    fullScreenIv.transitionName = post.id

    Glide.with(fullScreenIv)
      .load(post.url)
      .thumbnail(Glide.with(fullScreenIv.context).load(post.thumbnail))
      .diskCacheStrategy(DiskCacheStrategy.ALL)
      .listener(imageLoadingListener)
      .into(fullScreenIv)
  }

  override fun initListeners() {
    fullScreenBtnBack.onClick { fragmentManager?.popBackStack() }
    fullScreenBtnDownload.onClick { presenter.downloadImage() }
  }

  override fun setTitle(title: String) {
    fullScreenTvTitle.text = title
  }

  override fun injectDaggerDependency() {
    fullScreenImageComponent(activity!!).inject(this)
  }

  override fun showToast(textId: Int) {
    toast(textId)
  }

  private val imageLoadingListener = object : RequestListener<Drawable> {
    override fun onLoadFailed(
      e: GlideException?,
      model: Any,
      target: Target<Drawable>,
      isFirstResource: Boolean
    ): Boolean {
      startPostponedEnterTransition()
      return false
    }

    override fun onResourceReady(
      resource: Drawable,
      model: Any,
      target: Target<Drawable>,
      dataSource: DataSource,
      isFirstResource: Boolean
    ): Boolean {
      startPostponedEnterTransition()
      return false
    }
  }
}
