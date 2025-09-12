package com.chatia.project

import androidx.compose.runtime.Composable
import com.chatia.project.routingComposable.RoutingScreen
import com.chatia.ui.theme.baseTheme.ChatiaTheme
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    ChatiaTheme {
        RoutingScreen()
    }
}

