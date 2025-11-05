package com.chatia.project

import androidx.compose.ui.uikit.OnFocusBehavior
import androidx.compose.ui.window.ComposeUIViewController
import com.chatia.project.di.initKoin

fun MainViewController() = ComposeUIViewController(configure = {
    onFocusBehavior = OnFocusBehavior.FocusableAboveKeyboard
    initKoin()
}) { App() }

