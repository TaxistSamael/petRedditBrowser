package com.kek.redditfeed.domain.interactor

import com.kek.redditfeed.base.domain.ObservableUseCase
import com.kek.redditfeed.data.permission.Permission
import com.kek.redditfeed.data.permission.PermissionStatus
import com.kek.redditfeed.domain.repository.PermissionsRepository
import io.reactivex.Observable
import javax.inject.Inject

class RequestPermissionsUseCase @Inject constructor(
  private val permissionsRepository: PermissionsRepository
) : ObservableUseCase<Pair<Permission, PermissionStatus>, RequestPermissionsUseCase.Params>() {

  override fun buildObservableUseCase(
    params: Params
  ): Observable<Pair<Permission, PermissionStatus>> {
    return permissionsRepository.getRequestPermissionsObservable(*params.permissions)
  }

  class Params(val permissions: Array<Permission>) {
    constructor(permission: Permission) : this(arrayOf(permission))
  }
}