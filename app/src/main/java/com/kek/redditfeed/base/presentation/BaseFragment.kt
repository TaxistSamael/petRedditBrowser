package com.kek.redditfeed.base.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment

abstract class BaseFragment : MvpAppCompatFragment() {

  abstract val layoutRes: Int

  override fun onCreate(savedInstanceState: Bundle?) {
    injectDaggerDependency()
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    return inflater.inflate(layoutRes, container, false)
  }

  abstract fun injectDaggerDependency()
}