package com.chatia.presentation.permission

interface PermissionResultCallback {
    fun onPermissionGranted()
    fun onPermissionDenied(permissions :Map<String, PermissionState>)
}
