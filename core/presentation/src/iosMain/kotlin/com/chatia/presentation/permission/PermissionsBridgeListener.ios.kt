package com.chatia.presentation.permission

import org.koin.core.Koin
import kotlin.experimental.ExperimentalObjCName


@OptIn(ExperimentalObjCName::class)
@ObjCName(swiftName = "PermissionRequestProtocol")
actual interface PermissionsBridgeListener {
    actual fun requestAppPermissions(callback: PermissionResultCallback)
    actual fun areAppPermissionsGranted(): Boolean
    actual fun openSettings()
}

@Suppress("unused")
fun registerPermissionHandler(listener: PermissionsBridgeListener){
    Koin().get<PermissionBridge>().setListener(listener)
}