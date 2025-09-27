package com.chatia.navigator.destination.screensDestination

import com.chatia.navigator.destination.navigationDestination.NavigationDestination
import com.chatia.navigator.screens.Screens


const val PERMISSIONS_SCREEN_ROUTE = "PermissionsRoute"

object PermissionsScreenDestination : NavigationDestination {
    override fun route(): String = Screens.PermissionsScreenRoute.route

}