package com.kek.redditfeed.data.mapper

import android.Manifest
import com.kek.redditfeed.base.domain.Mapper
import com.kek.redditfeed.data.permission.Permission
import javax.inject.Inject

class AndroidPermissionMapper @Inject constructor() : Mapper<String, Permission>() {

  override fun map(from: String): Permission {
    return when (from) {
      Manifest.permission.CAMERA -> Permission.CAMERA
      Manifest.permission.ACCESS_FINE_LOCATION -> Permission.LOCATION
      Manifest.permission.WRITE_EXTERNAL_STORAGE -> Permission.WRITE_STORAGE
      Manifest.permission.READ_EXTERNAL_STORAGE -> Permission.READ_STORAGE
      Manifest.permission.READ_CONTACTS -> Permission.READ_CONTACTS
      else -> throw IllegalArgumentException("$from permission is not mapped, do it yourself")
    }
  }

  override fun reverse(to: Permission): String {
    return when (to) {
      Permission.CAMERA -> Manifest.permission.CAMERA
      Permission.LOCATION -> Manifest.permission.ACCESS_FINE_LOCATION
      Permission.WRITE_STORAGE -> Manifest.permission.WRITE_EXTERNAL_STORAGE
      Permission.READ_STORAGE -> Manifest.permission.READ_EXTERNAL_STORAGE
      Permission.READ_CONTACTS -> Manifest.permission.READ_CONTACTS
      else -> throw IllegalArgumentException("$to permission is not mapped, do it yourself")
    }
  }

}