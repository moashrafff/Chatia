package com.chatia.project.graphDestination

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.chatia.login.presentation.EnablePermissionsScreen
import com.chatia.login.presentation.protocol.LoginEffect
import com.chatia.login.presentation.screen.LoginScreen
import com.chatia.login.presentation.viewmodel.LoginViewModel
import com.chatia.navigator.core.AppNavigator
import com.chatia.navigator.destination.navigationDestination.NavigationDestination
import com.chatia.navigator.destination.screensDestination.LoginDestination
import com.chatia.navigator.destination.screensDestination.OTPDestination
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
                    OnBoardingEffect.NavigateToRegister -> appNavigator.navigate(OTPDestination.route())
                }
            }
        }
        OnBoardingScreen(viewmodel::setIntent)
    },
    LoginDestination to { appNavigator, navHostController ->
        val viewmodel: LoginViewModel = koinViewModel()
        val uiState = viewmodel.uiState.collectAsState()
        LaunchedEffect(Unit) {
            viewmodel.viewEffect.collect { output ->
                when (output) {
                    is LoginEffect.NavigateToForgetPassword -> Unit
                    is LoginEffect.NavigateToHome -> Unit
                    is LoginEffect.NavigateToRegister -> Unit
                    is LoginEffect.ShowError -> Unit
                }
            }
        }
        LoginScreen(
            loginViewState = uiState.value,
            onIntentChange = viewmodel::sendIntent
        )
    },
    OTPDestination to { appNavigator, navHostController ->
        EnablePermissionsScreen()
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