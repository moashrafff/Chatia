package com.chatia.login.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.chatia.presentation.applyIf
import com.chatia.project.isAndroid
import com.chatia.ui.components.verticalGradientStops

@Composable
fun LoginScreen(){
    Column(
        modifier = Modifier
            .verticalGradientStops(
                0.12f to Color(0xFFFFFFFF), 0.24f to Color(0xFFFFE5F9), 0.66f to Color(0xFFFFF7EB)
            )
            .safeContentPadding()
            .applyIf(condition = isAndroid(), modifier = {padding(bottom = 8.dp)})
            .fillMaxSize()
    ) {

    }
}