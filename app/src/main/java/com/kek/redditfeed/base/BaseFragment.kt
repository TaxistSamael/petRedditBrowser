package com.kek.redditfeed.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment

abstract class BaseFragment : MvpAppCompatFragment() {

  abstract val layoutRes: Int

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
    inflater.inflate(layoutRes, container, false)

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    injectDaggerDependency()
    super.onActivityCreated(savedInstanceState)
  }

  override fun onDestroy() {
    removeDaggerDependency()
    super.onDestroy()
  }

  abstract fun injectDaggerDependency()

  open fun removeDaggerDependency() {} //do we need it???
}