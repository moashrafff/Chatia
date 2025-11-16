package com.chatia.navigator.destination.screensDestination

import com.chatia.navigator.destination.navigationDestination.NavigationDestination
import com.chatia.navigator.screens.Screens

const val REGISTER_ROUTE = "RegisterRoute"

object RegisterDestination : NavigationDestination {
    override fun route(): String = Screens.RegisterScreenRoute.route
}