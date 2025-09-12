package com.chatia.onBoarding.presentation.protocol

sealed class OnBoardingIntent {
    data object LoginButtonClicked : OnBoardingIntent()
    data object RegisterButtonClicked : OnBoardingIntent()
}

sealed class OnBoardingEffect {
    data object NavigateToLogin : OnBoardingEffect()
    data object NavigateToRegister : OnBoardingEffect()
}