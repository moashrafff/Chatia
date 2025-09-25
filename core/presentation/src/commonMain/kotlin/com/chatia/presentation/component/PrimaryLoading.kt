package com.chatia.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chatia.ui.components.loading.EllipsisLoaderMultiPng

@Composable
fun PrimaryLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        EllipsisLoaderMultiPng(
            dotResIds = listOf(
                "ellipsis_33",
                "ellipsis_34",
                "ellipsis_35",
                "ellipsis_36"
            ),
            dotBaseSize = 16.dp,
            dotSpacing = 12.dp,
            minScale = 0.55f,
            maxScale = 1.5f,
            cycleDurationMs = 850,
            travelRight = true
        )
    }

}