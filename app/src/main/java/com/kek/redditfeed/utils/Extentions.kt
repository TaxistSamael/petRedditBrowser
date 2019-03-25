package com.kek.redditfeed.utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import java.io.File

fun getMimeType(context: Context, uri: Uri): String? {
  val extension: String?

  //Check uri format to avoid null
  extension = if (uri.scheme == ContentResolver.SCHEME_CONTENT) {
    //If scheme is a content
    val mime = MimeTypeMap.getSingleton()
    mime.getExtensionFromMimeType(context.contentResolver.getType(uri))
  } else {
    //If scheme is a File
    //This will replace white spaces with %20 and also other special characters. This will avoid returning null values on file name with spaces and special characters.
    MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(File(uri.path)).toString())
  }

  return extension
}