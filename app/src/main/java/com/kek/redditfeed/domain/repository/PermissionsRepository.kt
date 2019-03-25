package com.kek.redditfeed.domain.repository

import com.kek.redditfeed.data.permission.Permission
import com.kek.redditfeed.data.permission.PermissionStatus
import io.reactivex.Observable

interface PermissionsRepository {
  fun getShouldShowRationaleForPermissionsObservable(vararg permission: Permission): Observable<Pair<Permission, Boolean>>

  fun getRequestPermissionsObservable(vararg permission: Permission): Observable<Pair<Permission, PermissionStatus>>

  fun isPermissionGranted(permission: Permission): Boolean
}