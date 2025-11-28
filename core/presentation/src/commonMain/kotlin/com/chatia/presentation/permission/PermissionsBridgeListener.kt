package com.chatia.presentation.permission

expect interface PermissionsBridgeListener {
    fun requestAppPermissions(callback: PermissionResultCallback)
    fun areAppPermissionsGranted(): Boolean
    fun openSettings()
}
