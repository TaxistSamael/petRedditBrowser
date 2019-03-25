package com.kek.redditfeed.data.repository

import android.app.Activity
import com.kek.redditfeed.base.di.scopes.PerActivity
import com.kek.redditfeed.data.mapper.AndroidPermissionMapper
import com.kek.redditfeed.data.permission.Permission
import com.kek.redditfeed.data.permission.PermissionStatus
import com.kek.redditfeed.domain.repository.PermissionsRepository
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

@PerActivity
class PermissionsRepositoryImpl @Inject constructor(
  private val activity: Activity,
  private val rxPermissions: RxPermissions,
  private val permissionsMapper: AndroidPermissionMapper
) : PermissionsRepository {

  override fun getRequestPermissionsObservable(vararg permission: Permission)
      : Observable<Pair<Permission, PermissionStatus>> {
    return rxPermissions.requestEach(*convertPermissions(*permission))
      .map {
        val status = when {
          it.granted -> PermissionStatus.GRANTED
          it.shouldShowRequestPermissionRationale -> PermissionStatus.RATIONALE
          else -> PermissionStatus.DENIED
        }
        permissionsMapper.map(it.name) to status
      }
  }

  override fun getShouldShowRationaleForPermissionsObservable(vararg permission: Permission)
      : Observable<Pair<Permission, Boolean>> {
    val androidPermissions = convertPermissions(*permission)
    return Observable.fromArray(*androidPermissions)
      .zipWith(
        rxPermissions.shouldShowRequestPermissionRationale(activity, *androidPermissions),
        BiFunction { perm, shouldShowRationale ->
          permissionsMapper.map(perm) to shouldShowRationale
        }
      )
  }

  override fun isPermissionGranted(permission: Permission): Boolean {
    return rxPermissions.isGranted(permissionsMapper.reverse(permission))
  }

  private fun convertPermissions(vararg permissions: Permission): Array<String> {
    return permissions.map { permissionsMapper.reverse(it) }.toTypedArray()
  }
}