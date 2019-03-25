package com.kek.redditfeed.base.presentation

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity

abstract class BaseActivity : MvpAppCompatActivity() {

  protected abstract val layoutRes: Int

  override fun onCreate(savedInstanceState: Bundle?) {
    injectDependency()
    super.onCreate(savedInstanceState)
    setContentView(layoutRes)
  }

  abstract fun injectDependency()
}