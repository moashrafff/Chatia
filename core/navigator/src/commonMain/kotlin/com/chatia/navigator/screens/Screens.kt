package com.chatia.navigator.screens

import com.chatia.navigator.destination.screensDestination.LOGIN_ROUTE
import com.chatia.navigator.destination.screensDestination.ONBOARDING_ROUTE
import com.chatia.navigator.destination.screensDestination.PERMISSIONS_SCREEN_ROUTE

sealed class Screens(val route: String) {
    data object OnBoardingScreenRoute : Screens(ONBOARDING_ROUTE)
    data object LoginScreenRoute : Screens(LOGIN_ROUTE)

    data object PermissionsScreenRoute : Screens(PERMISSIONS_SCREEN_ROUTE)


}