package com.chatia.navigator.event

import androidx.navigation.NavOptionsBuilder

sealed class NavigationEvent {
    data object NavigateUp : NavigationEvent()

    data object PopBackStack : NavigationEvent()

    class Directions(
        val destination: String,
        val builder: NavOptionsBuilder.() -> Unit,
    ) : NavigationEvent()
}