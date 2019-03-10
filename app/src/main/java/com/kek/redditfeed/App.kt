package com.kek.redditfeed

import android.app.Application
import com.kek.redditfeed.base.di.AppComponent
import com.kek.redditfeed.base.di.DaggerAppComponent
import com.kek.redditfeed.base.di.module.AppModule

class App : Application() {
  companion object {
    lateinit var app: App
    lateinit var appComponent: AppComponent
  }

  override fun onCreate() {
    super.onCreate()
    app = this
    appComponent = DaggerAppComponent.builder()
        .appModule(AppModule(this))
        .build()
  }
}