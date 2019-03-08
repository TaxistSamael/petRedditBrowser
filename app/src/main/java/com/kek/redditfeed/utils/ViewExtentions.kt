package com.kek.redditfeed.utils

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequestBuilder

fun View.onClick(call: () -> Unit) {
  setOnClickListener { call.invoke() }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View =
  LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

fun RecyclerView.doOnNextPage(call: () -> Unit, scrollOffset: Int = 4) {
  addOnScrollListener(object : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
      with(recyclerView.layoutManager as LinearLayoutManager) {
        val lastVisibleItemPosition = findLastVisibleItemPosition()
        if ((itemCount - lastVisibleItemPosition) <= scrollOffset) call.invoke()
      }
    }
  })
}

fun SimpleDraweeView.setImageUri(url: String) {
  val request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
    .setProgressiveRenderingEnabled(true)
    .build()

  val newController = Fresco.newDraweeControllerBuilder()
    .setImageRequest(request)
    .setOldController(controller)
    .build()
  controller = newController
}
