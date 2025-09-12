package com.chatia.navigator.core

import androidx.navigation.NavOptionsBuilder
import com.chatia.navigator.event.NavigationEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class AppNavigatorImpl() : AppNavigator {
  private val navigationEvents = Channel<NavigationEvent>()

  override fun navigateUp(): Boolean =
    navigationEvents.trySend(NavigationEvent.NavigateUp).isSuccess

  override fun popBackStack() {
    navigationEvents.trySend(NavigationEvent.PopBackStack)
  }

  override fun navigate(destination: String, builder: NavOptionsBuilder.() -> Unit): Boolean =
    navigationEvents.trySend(
      NavigationEvent.Directions(
        destination = destination,
        builder = builder,
      ),
    ).isSuccess

  override val destinations: Flow<NavigationEvent>
    get() = navigationEvents.receiveAsFlow()
}
