package com.chatia.project.routingComposable

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.chatia.navigator.core.AppNavigator
import com.chatia.navigator.event.NavigationEvent
import com.chatia.navigator.screens.Screens
import com.chatia.presentation.applyIf
import com.chatia.project.graphDestination.addComposableDestinations
import com.chatia.project.isAndroid
import org.koin.compose.koinInject

@Composable
fun RoutingScreen() {
    val navigator = koinInject<AppNavigator>()
    AppScaffold(appNavigator = navigator)
}


@Composable
fun AppScaffold(
    appNavigator: AppNavigator
) {
    val navController = rememberNavController()

    LaunchedEffect(navController)  {
        appNavigator.destinations.collect { event ->
            when (event) {
                is NavigationEvent.Directions -> {

                    navController.navigate(
                        event.destination,
                        event.builder,
                    )
                }

                NavigationEvent.NavigateUp -> {
                    navController.navigateUp()
                }

                NavigationEvent.PopBackStack -> {
                    navController.popBackStack()
                }
            }
        }
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .applyIf(condition = isAndroid(), modifier = {statusBarsPadding()}),
        content = {
            NavHost(
                navController = navController,
                startDestination = Screens.OnBoardingScreenRoute.route,
                enterTransition = { fadeIn(animationSpec = tween(500)) },
                exitTransition = { fadeOut(animationSpec = tween(500)) },
            ) {
                addComposableDestinations(
                    appNavigator,
                    navController
                )
            }
        },
    )
}




