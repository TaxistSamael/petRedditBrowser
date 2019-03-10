package com.kek.redditfeed.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
