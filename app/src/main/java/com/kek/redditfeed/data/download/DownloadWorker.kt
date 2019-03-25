package com.kek.redditfeed.data.download

import android.content.Context
import android.graphics.Bitmap.CompressFormat.JPEG
import android.media.MediaScannerConnection
import android.os.Environment
import android.util.Log
import androidx.work.ListenableWorker.Result.failure
import androidx.work.ListenableWorker.Result.success
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.bumptech.glide.Glide
import java.io.File
import java.io.FileOutputStream

const val URL_DATA_PARAMETER = "url"
private const val IMAGE_FILE_SUFFIX = ".jpg"
private const val IMAGE_FILE_PREFIX = "img_"
private const val IMAGE_QUALITY_PERCENT = 100

//todo: update type of loading if needed
class DownloadWorker(private val context: Context, params: WorkerParameters) : Worker(context, params) {


  override fun doWork(): Result {

    val url = inputData.getString(URL_DATA_PARAMETER) ?: return failure()

    Log.d("PIDR", "doWork. url: $url")

    val futureBitmap = Glide.with(context)
      .asBitmap()
      .load(url)
      .submit()

    try {
      val bitmap = futureBitmap.get()
      val file = createImageFile()
      FileOutputStream(file).use {
        val isSuccessful = bitmap.compress(JPEG, IMAGE_QUALITY_PERCENT, it)
        bitmap.recycle()
        return if (isSuccessful) {
          showImageInDeviceGallery(file)
          success()
        } else {
          failure()
        }
      }
    } catch (exception: Exception) {
      Log.d("PIDR", "bitmap.get() exception: ${exception.message}")
      return failure()
    }
  }

  private fun createImageFile(): File {
    val imageFileName = IMAGE_FILE_PREFIX + System.currentTimeMillis() + IMAGE_FILE_SUFFIX
    val storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
    Log.d("PIDR", "createImageFile")
    return File(storageDir, imageFileName)
  }

  private fun showImageInDeviceGallery(file: File) {
    MediaScannerConnection.scanFile(context, arrayOf(file.absolutePath), null, null)
    Log.d("PIDR", "showImageInDeviceGallery")
  }
}