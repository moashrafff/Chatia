package com.chatia.project.graphDestination

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.chatia.navigator.core.AppNavigator
import com.chatia.navigator.destination.navigationDestination.NavigationDestination
import com.chatia.navigator.destination.screensDestination.OnBoardingDestination
import com.chatia.onBoarding.presentation.screen.OnBoardingScreen

private val composableDestinations: Map<NavigationDestination, @Composable (
    AppNavigator,
    NavHostController,
) -> Unit> = mapOf(

    OnBoardingDestination to { appNavigator, navHostController ->
        OnBoardingScreen()
    }

)


fun NavGraphBuilder.addComposableDestinations(
    appNavigator: AppNavigator,
    navHostController: NavHostController,
) {
    composableDestinations.forEach { entry ->
        val destination = entry.key
        composable(
            route = destination.route(),
            arguments = destination.arguments,
            deepLinks = destination.deepLinks,
        ) {
            entry.value(
                appNavigator,
                navHostController,
            )
        }
    }
}