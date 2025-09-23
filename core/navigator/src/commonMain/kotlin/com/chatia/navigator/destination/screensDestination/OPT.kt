package com.chatia.navigator.destination.screensDestination

import com.chatia.navigator.destination.navigationDestination.NavigationDestination
import com.chatia.navigator.screens.Screens


const val OTP_ROUTE = "OTPRoute"

object OTPDestination : NavigationDestination {
    override fun route(): String = Screens.OTPScreenRoute.route

}