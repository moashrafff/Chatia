package com.chatia.project.graphDestination

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.chatia.login.presentation.PermissionsScreen.EnablePermissionsScreen
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
import com.chatia.presentation.permission.PermissionBridge
import com.chatia.presentation.permission.PermissionResultCallback
import com.chatia.presentation.permission.PermissionState
import com.chatia.register.presentation.protocol.RegisterEffect
import com.chatia.register.presentation.screen.RegisterScreen
import com.chatia.register.presentation.viewmodel.RegisterViewmodel
import org.koin.compose.getKoin
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
        val koin = getKoin()

        var showDialog by remember {
            mutableStateOf(false)
        }
        var isPermanentDenial by remember {
            mutableStateOf(false)
        }
        var deniedPermissions by remember {
            mutableStateOf<List<String>>(emptyList())
        }

        LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
            if (koin.get<PermissionBridge>().areAppPermissionsGranted()) {
                appNavigator.navigate(OnBoardingDestination.route())//todo navigate to home
            }
        }

        fun requestPermission(){
            koin.get<PermissionBridge>()
                .requestAppPermissions(object : PermissionResultCallback {
                    override fun onPermissionGranted() {
                        appNavigator.navigate(OnBoardingDestination.route())//todo navigate to home
                    }

                    override fun onPermissionDenied(permissions: Map<String, PermissionState>) {
                        deniedPermissions = permissions.keys.toList()
                        if(permissions.any { it.value == PermissionState.PERMANENTLY_DENIED }){
                            isPermanentDenial = true
                            showDialog = true
                        } else if(permissions.any { it.value == PermissionState.SHOULD_SHOW_RATIONALE }){
                            isPermanentDenial = false
                            showDialog = true
                        }
                    }


                })
        }


        if (showDialog) {

            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = {
                    Text(
                        text = "Permission required to continue",
                    )
                },
                text = {
                    val deniedList = if (deniedPermissions.isNotEmpty()) {
                        deniedPermissions.joinToString(", ")
                    } else {
                        "the necessary permissions"
                    }

                    Text(
                        text = if (isPermanentDenial) {
                            "You denied: $deniedList. Please go to Settings to enable them manually."
                        } else {
                            "To work properly with all features, you need to grant: $deniedList. Please tap Continue."
                        },
                    )
                },
                confirmButton = {
                    TextButton(onClick = {
                        showDialog = false
                        if (isPermanentDenial) {
                            koin.get<PermissionBridge>().openSettings()
                        } else {
                            requestPermission()
                        }
                    }) {
                        Text(if (isPermanentDenial) "Go to Settings" else "Continue")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showDialog = false
                        appNavigator.navigate(OnBoardingDestination.route())//todo navigate to home
                    }) {
                        Text(if (isPermanentDenial) "Continue" else "Cancel")
                    }
                }
            )
        }

        EnablePermissionsScreen(){
                requestPermission()
        }
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
    RegisterDestination to { appNavigator, navHostController ->
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