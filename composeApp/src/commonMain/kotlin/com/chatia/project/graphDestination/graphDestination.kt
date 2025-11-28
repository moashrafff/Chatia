package com.chatia.project.graphDestination

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.chatia.login.presentation.ChaiaPlusSubcriptionScreen.ChatiaPlusSubscriptionScreen
import com.chatia.login.presentation.protocol.LoginEffect
import com.chatia.login.presentation.screen.LoginScreen
import com.chatia.login.presentation.viewmodel.LoginViewModel
import com.chatia.navigator.core.AppNavigator
import com.chatia.navigator.destination.navigationDestination.NavigationDestination
import com.chatia.navigator.destination.screensDestination.LoginDestination
import com.chatia.navigator.destination.screensDestination.OnBoardingDestination
import com.chatia.navigator.destination.screensDestination.PermissionsScreenDestination
import com.chatia.navigator.destination.screensDestination.RegisterDestination
import com.chatia.onBoarding.presentation.protocol.OnBoardingEffect
import com.chatia.onBoarding.presentation.screen.OnBoardingScreen
import com.chatia.onBoarding.presentation.viewmodel.OnBoardingViewModel
import com.chatia.register.presentation.protocol.RegisterEffect
import com.chatia.register.presentation.screen.RegisterScreen
import com.chatia.register.presentation.viewmodel.RegisterViewmodel
import org.koin.compose.viewmodel.koinViewModel

private val composableDestinations: Map<NavigationDestination, @Composable (
    AppNavigator,
    NavHostController,
) -> Unit> = mapOf(
    OnBoardingDestination to { appNavigator, _ ->
        val viewmodel: OnBoardingViewModel = koinViewModel()
        LaunchedEffect(Unit) {
            viewmodel.viewEffect.collect { output ->
                when (output) {
                    OnBoardingEffect.NavigateToLogin -> appNavigator.navigate(LoginDestination.route())
                    OnBoardingEffect.NavigateToRegister -> appNavigator.navigate(RegisterDestination.route())
                }
            }
        }
        OnBoardingScreen(viewmodel::setIntent)
    },

    PermissionsScreenDestination to { appNavigator, navHostController ->
//        EnablePermissionsScreen()
        val remeberListState = remember { mutableStateListOf("", "", "", "") }
//        OTPScreen(remeberListState)
        ChatiaPlusSubscriptionScreen()
    },
    LoginDestination to { appNavigator, navHostController ->
        val viewmodel: LoginViewModel = koinViewModel()
        val stateRenderer by viewmodel.stateRendererFlow.collectAsState()
        LaunchedEffect(Unit) {
            viewmodel.viewEffect.collect { output ->
                when (output) {
                    is LoginEffect.NavigateToForgetPassword -> Unit
                    is LoginEffect.NavigateToHome -> Unit
                    is LoginEffect.NavigateToRegister -> appNavigator.navigate(RegisterDestination.route())
                    is LoginEffect.ShowError -> Unit
                }
            }
        }
        LoginScreen(
            stateRenderer = stateRenderer, onIntentChange = viewmodel::sendIntent
        )
    },
    PermissionsScreenDestination to { appNavigator, navHostController ->
//        EnablePermissionsScreen()
        val remeberListState = remember { mutableStateListOf("","","","")  }// save till implement full OTP cycle
//        OTPScreen(remeberListState)
        ChatiaPlusSubscriptionScreen()
    }
)
    }, RegisterDestination to { appNavigator, navHostController ->
        val viewmodel: RegisterViewmodel = koinViewModel()
        val stateRenderer by viewmodel.stateRendererFlow.collectAsState()
        LaunchedEffect(Unit) {
            viewmodel.viewEffect.collect { output ->
                when (output) {
                    is RegisterEffect.NavigateToHome -> Unit
                    is RegisterEffect.NavigateToLogin -> appNavigator.navigate(LoginDestination.route())
                }
            }
        }
        RegisterScreen(stateRenderer = stateRenderer, sendIntent = viewmodel::sendIntent)
    })

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