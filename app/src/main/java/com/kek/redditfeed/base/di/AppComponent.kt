package com.kek.redditfeed.base.di

import com.kek.redditfeed.base.di.module.ApiModule
import com.kek.redditfeed.base.di.module.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class])
interface AppComponent {

}