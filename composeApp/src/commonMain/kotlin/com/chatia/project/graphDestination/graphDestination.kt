package com.chatia.project.graphDestination

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.chatia.login.presentation.screen.LoginScreen
import com.chatia.navigator.core.AppNavigator
import com.chatia.navigator.destination.navigationDestination.NavigationDestination
import com.chatia.navigator.destination.screensDestination.LoginDestination
import com.chatia.navigator.destination.screensDestination.OnBoardingDestination
import com.chatia.onBoarding.presentation.protocol.OnBoardingEffect
import com.chatia.onBoarding.presentation.screen.OnBoardingScreen
import com.chatia.onBoarding.presentation.viewmodel.OnBoardingViewModel
import org.koin.compose.viewmodel.koinViewModel

private val composableDestinations: Map<NavigationDestination, @Composable (
    AppNavigator,
    NavHostController,
) -> Unit> = mapOf(
    OnBoardingDestination to { appNavigator, navHostController ->
        val viewmodel: OnBoardingViewModel = koinViewModel()
        LaunchedEffect(Unit) {
            viewmodel.viewEffect.collect { output ->
                when (output) {
                    OnBoardingEffect.NavigateToLogin -> appNavigator.navigate(LoginDestination.route())
                    OnBoardingEffect.NavigateToRegister -> Unit
                }
            }
        }
        OnBoardingScreen(viewmodel::setIntent)
    },
    LoginDestination to { appNavigator, navHostController ->
        LoginScreen()
    },
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