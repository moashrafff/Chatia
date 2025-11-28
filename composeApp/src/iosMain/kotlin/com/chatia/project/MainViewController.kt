package com.chatia.project

import androidx.compose.ui.uikit.OnFocusBehavior
import androidx.compose.ui.window.ComposeUIViewController
import com.chatia.presentation.permission.PermissionBridge
import com.chatia.project.di.initKoin
import org.koin.core.Koin

fun MainViewController() = ComposeUIViewController(configure = {
    onFocusBehavior = OnFocusBehavior.FocusableAboveKeyboard
    initKoin()
    val koin = org.koin.mp.KoinPlatformTools.defaultContext().get()
    val permissionBridge = koin.get<PermissionBridge>()
    permissionBridge.setListener(IosPermissionHandler())
}) { App() }

