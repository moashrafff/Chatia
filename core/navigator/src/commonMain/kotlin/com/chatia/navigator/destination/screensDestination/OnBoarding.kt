package com.chatia.navigator.destination.screensDestination

import com.chatia.navigator.destination.navigationDestination.NavigationDestination
import com.chatia.navigator.screens.Screens

const val ONBOARDING_ROUTE = "OnBoardingRoute"

object OnBoardingDestination : NavigationDestination {
    override fun route(): String = Screens.OnBoardingScreenRoute.route
}