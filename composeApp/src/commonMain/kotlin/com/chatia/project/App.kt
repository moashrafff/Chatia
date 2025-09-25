package com.chatia.project

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.chatia.project.routingComposable.RoutingScreen
import com.chatia.ui.theme.baseTheme.ChatiaTheme
import com.mmk.kmpauth.google.GoogleAuthCredentials
import com.mmk.kmpauth.google.GoogleAuthProvider


@Composable
fun App() {
    var authReady by remember { mutableStateOf(false) }
    LaunchedEffect(Unit){
        GoogleAuthProvider.create(
            credentials = GoogleAuthCredentials(
                serverId = "1043189848944-9ovfiq46ce4f5ne4qbi6oktaee4oe15o.apps.googleusercontent.com"
            )
        )
        authReady = true
    }
    if (authReady){
        ChatiaTheme {
            RoutingScreen()
        }
    }
}

