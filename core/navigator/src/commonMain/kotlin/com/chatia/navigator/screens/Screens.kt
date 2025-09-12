package com.chatia.navigator.screens

import com.chatia.navigator.destination.screensDestination.ONBOARDING_ROUTE

sealed class Screens(val route: String) {
    data object OnBoardingScreenRoute : Screens(ONBOARDING_ROUTE)
}