package com.chatia.register.presentation.screen

import androidx.compose.runtime.Composable
import com.chatia.presentation.stateRenderer.StateRenderer
import com.chatia.register.presentation.model.RegisterUIModel
import com.chatia.register.presentation.protocol.RegisterIntent
import com.chatia.register.presentation.protocol.RegisterUiState

@Composable
fun RegisterScreen(stateRenderer : StateRenderer<RegisterUiState, RegisterUIModel>, sendIntent: (RegisterIntent) -> Unit) {
    StateRenderer.of(
        statRenderer = stateRenderer
    ){
        onUiState {
            RegisterUiContent(uiState = it, sendIntent = sendIntent)
        }
        onSuccess { uIModel ->
            RegisterUiContent(sendIntent = sendIntent)
        }
        onErrorState {
            RegisterUiContent(uiState = it, sendIntent = sendIntent)
        }
    }
}