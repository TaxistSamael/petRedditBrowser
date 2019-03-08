package com.kek.redditfeed.base.di.module

import android.app.Application
import com.google.gson.GsonBuilder
import com.kek.redditfeed.BuildConfig.BASE_URL
import com.kek.redditfeed.main.feed.data.net.FeedService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

  @Provides
  @Singleton
  fun providesOkHttpCache(application: Application): Cache {
    val size: Long = 10 * 1024 * 1024 // 10 MB
    return Cache(application.cacheDir, size)
  }

  @Provides
  @Singleton
  fun provideGsonBuilder(): GsonBuilder {
    return GsonBuilder()
  }

  @Provides
  @Singleton
  fun provideOkHttpClient(loggingInterceptor: Interceptor, cache: Cache): OkHttpClient {
    return OkHttpClient.Builder()
      .cache(cache)
      .addInterceptor(loggingInterceptor)
      .build()
  }

  @Provides
  @Singleton
  fun provideLoggingInterceptor(): Interceptor {
    return HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BODY
    }
  }

  @Provides
  @Singleton
  fun provideRetrofit(gsonBuilder: GsonBuilder, okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .baseUrl(BASE_URL)
      .build()
  }

  @Provides
  @Singleton
  fun provideFeedInterface(retrofit: Retrofit): FeedService {
    return retrofit.create(FeedService::class.java)
  }
}