package com.chatia.login.presentation.screen

import androidx.compose.runtime.Composable
import com.chatia.login.presentation.model.LoginUIModel
import com.chatia.login.presentation.protocol.LoginIntent
import com.chatia.login.presentation.protocol.LoginState
import com.chatia.presentation.stateRenderer.StateRenderer

@Composable
fun LoginScreen(
    stateRenderer: StateRenderer<LoginState, LoginUIModel>,
    onIntentChange: (LoginIntent) -> Unit
) {
    StateRenderer.of(
        statRenderer = stateRenderer,
        retryAction = { onIntentChange.invoke(LoginIntent.OnLoginClicked) },
    ) {
        onUiState { updatedState ->
            LoginUiContent(updatedState, onIntentChange)
        }
        onLoadingState {}
        onSuccess { loginUiModel ->
        }
        onEmpty {}
        onErrorState { updatedState ->
            LoginUiContent(updatedState, onIntentChange)
        }
    }
}