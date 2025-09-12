package com.chatia.navigator.core

import androidx.navigation.NavOptionsBuilder
import com.chatia.navigator.event.NavigationEvent
import kotlinx.coroutines.flow.Flow

interface AppNavigator {
  fun navigateUp(): Boolean

  fun popBackStack()

  fun navigate(
    destination: String,
    builder: NavOptionsBuilder.() -> Unit = { launchSingleTop = true },
  ): Boolean

  val destinations: Flow<NavigationEvent>
}
