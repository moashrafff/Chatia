package com.chatia.presentation.permission

actual interface PermissionsBridgeListener {
    actual fun requestAppPermissions(callback: com.chatia.presentation.permission.PermissionResultCallback)
    actual fun areAppPermissionsGranted(): Boolean
    actual fun openSettings()
}