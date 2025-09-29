package com.chatia.presentation.stateRenderer

import androidx.compose.runtime.Composable
import com.chatia.presentation.component.EmptyScreen
import com.chatia.presentation.component.ErrorOverlay
import com.chatia.presentation.component.PrimaryLoading
import com.chatia.domain.model.ErrorMessage
import com.chatia.presentation.model.UiText
import com.chatia.presentation.resources.Res
import com.chatia.presentation.resources.empty


sealed class StateRenderer<out S, O> {
    class ScreenContent<S, O>(val viewState: S) : StateRenderer<S, O>()
    data class LoadingScreen<S, O>(val viewState: S) : StateRenderer<S, O>()
    data class ErrorPopup<S, O>(val viewState: S, val errorMessage: ErrorMessage) : StateRenderer<S, O>()
    data class Empty<S, O>(val viewState: S, val emptyMessage: UiText = UiText.Resource(Res.string.empty)) : StateRenderer<S, O>()
    data class Success<S, O>(val output: O) : StateRenderer<S, O>()

    @Composable
    fun onUiState(action: @Composable (S) -> Unit): StateRenderer<S, O> {
        if (this is ScreenContent) {
            action(viewState)
        }
        return this
    }

    @Composable
    fun onLoadingState(action: @Composable (S) -> Unit): StateRenderer<S, O> {
        if (this is LoadingScreen) {
            action(viewState)
        }
        return this
    }

    @Composable
    fun onSuccess(action: @Composable (O) -> Unit): StateRenderer<S, O> {
        if (this is Success) {
            action(output)
        }
        return this
    }

    @Composable
    fun onErrorState(action: @Composable (S) -> Unit): StateRenderer<S, O> {
        if (this is ErrorPopup) {
            action(viewState)
        }
        return this
    }

    @Composable
    fun onEmpty(action: @Composable () -> Unit): StateRenderer<S, O> {
        if (this is Empty) {
            action()
        }
        return this
    }

    companion object {
        @Composable
        fun <S, O> of(
            retryAction: () -> Unit = {},
            statRenderer: StateRenderer<S, O>,
            blocK: @Composable StateRenderer<S, O>.() -> Unit,
        ): StateRenderer<S, O> {
            statRenderer.blocK() // show this first before doing any thing

            when (statRenderer) {
                is Empty -> EmptyScreen(emptyMessage = statRenderer.emptyMessage)
                is ErrorPopup -> ErrorOverlay(errorMessage = statRenderer.errorMessage)
                is LoadingScreen -> PrimaryLoading()
                else -> {}
            }
            return statRenderer
        }
    }

}