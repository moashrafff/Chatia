package com.chatia.presentation.permission

class PermissionBridge {

    private var listener: PermissionsBridgeListener? = null

    fun setListener(listener: PermissionsBridgeListener) {
        this.listener = listener
    }

    fun requestAppPermissions(callback: PermissionResultCallback) {
        listener?.requestAppPermissions(callback) ?: error("Callback handler not set")
    }

    fun areAppPermissionsGranted(): Boolean {
        return listener?.areAppPermissionsGranted() ?: false
    }
    fun openSettings() {
        listener?.openSettings()
    }

}
