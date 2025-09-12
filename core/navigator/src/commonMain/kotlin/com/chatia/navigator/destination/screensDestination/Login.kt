package com.chatia.navigator.destination.screensDestination

import com.chatia.navigator.destination.navigationDestination.NavigationDestination
import com.chatia.navigator.screens.Screens

const val LOGIN_ROUTE = "LoginRoute"

object LoginDestination : NavigationDestination {
    override fun route(): String = Screens.LoginScreenRoute.route
}