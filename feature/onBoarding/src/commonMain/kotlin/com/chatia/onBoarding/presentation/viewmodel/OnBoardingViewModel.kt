package com.chatia.onBoarding.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chatia.onBoarding.presentation.protocol.OnBoardingEffect
import com.chatia.onBoarding.presentation.protocol.OnBoardingIntent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class OnBoardingViewModel: ViewModel() {
    private val _viewEffect: Channel<OnBoardingEffect> = Channel()
    val viewEffect = _viewEffect.receiveAsFlow()

    private fun sendEffect(reducer: () -> OnBoardingEffect) {
        viewModelScope.launch {
            _viewEffect.send(reducer())
        }
    }

    fun setIntent(intent: OnBoardingIntent) {
        when (intent) {
            OnBoardingIntent.LoginButtonClicked -> sendEffect { OnBoardingEffect.NavigateToLogin }
            OnBoardingIntent.RegisterButtonClicked -> sendEffect { OnBoardingEffect.NavigateToRegister }
        }
    }
}