package com.kek.redditfeed.data.repository

import android.util.Log
import androidx.work.*
import com.kek.redditfeed.data.download.DownloadWorker
import com.kek.redditfeed.data.download.URL_DATA_PARAMETER
import com.kek.redditfeed.domain.repository.DownloadRepository
import io.reactivex.Completable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DownloadRepositoryImpl @Inject constructor(
  private val workManager: WorkManager
): DownloadRepository {

  //todo: retry policy
  override fun downloadImage(url: String): Completable {

    val data = Data.Builder()
      .putString(URL_DATA_PARAMETER, url)
      .build()

    val constraints = Constraints.Builder()
      .setRequiredNetworkType(NetworkType.CONNECTED)
      .setRequiresStorageNotLow(true)
      .build()

    val downloadWorker = OneTimeWorkRequest.Builder(DownloadWorker::class.java)
      .setInputData(data)
      .setConstraints(constraints)
      .build()

    Log.d("PIDR", "workManager.enqueueUniqueWork")
    workManager.enqueueUniqueWork(url, ExistingWorkPolicy.KEEP, downloadWorker)

    //todo: improve this shit
    return Completable.complete()
  }
}