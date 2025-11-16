package com.chatia.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.chatia.login.domain.usecase.LoginUseCase
import com.chatia.login.presentation.error.LoginUIError
import com.chatia.login.presentation.model.LoginUIModel
import com.chatia.login.presentation.protocol.LoginEffect
import com.chatia.login.presentation.protocol.LoginEffect.NavigateToForgetPassword
import com.chatia.login.presentation.protocol.LoginEffect.NavigateToRegister
import com.chatia.login.presentation.protocol.LoginIntent
import com.chatia.login.presentation.protocol.LoginState
import com.chatia.login.presentation.validation.LoginValidator
import com.chatia.presentation.stateRenderer.StateRenderer
import com.mmk.kmpauth.google.GoogleUser
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase, ): ViewModel() {

    private var loginState = LoginState()

    private val _stateRendererMutableState =
        MutableStateFlow<StateRenderer<LoginState, LoginUIModel>>(
            StateRenderer.ScreenContent(loginState)
        )
    val stateRendererFlow: StateFlow<StateRenderer<LoginState, LoginUIModel>> get() = _stateRendererMutableState

    private val _viewEffect: Channel<LoginEffect> = Channel()
    val viewEffect = _viewEffect.receiveAsFlow()

    fun sendIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.CreateAccountClicked -> sendEffect { NavigateToRegister }
            is LoginIntent.ForgetPasswordClicked -> sendEffect { NavigateToForgetPassword(loginState.loginUIModel.userName) }
            is LoginIntent.OnLoginClicked -> loginWithUserNameAndPassword(
                username = loginState.loginUIModel.userName,
                password = loginState.loginUIModel.password
            )
            is LoginIntent.LoginWithAppleClicked -> loginWithApple()
            is LoginIntent.LoginWithGoogleClickedResult -> loginWithGoogle(googleUser = intent.googleUser)
            is LoginIntent.UserNameUpdated -> updateState {
                copy(
                    loginUIModel = loginState.loginUIModel.copy(
                        userName = intent.userName
                    )
                )
            }
            is LoginIntent.PasswordUpdated -> updateState {
                copy(
                    loginUIModel = loginState.loginUIModel.copy(
                        password = intent.password
                    )
                )
            }
            is LoginIntent.RememberMeClicked -> toggleRememberMe()
            is LoginIntent.PasswordVisibleClicked -> togglePasswordVisibility()
        }
    }

    private fun loginWithGoogle(googleUser: GoogleUser?) {

    }

    private fun loginWithApple() {

    }

    private fun sendEffect(effect: () -> LoginEffect) {
        viewModelScope.launch {
            _viewEffect.send(effect())
        }
    }

    private inline fun updateState(crossinline reducer: LoginState.() -> LoginState) {
        loginState = loginState.reducer()
        validate()
    }

    private fun validate() {
        val passwordError: LoginUIError =
            LoginValidator.passwordError(loginState.loginUIModel.password)
        val isLoginButtonEnabled: Boolean =
            LoginValidator.isLoginValid(passwordError)

        loginState = loginState.copy(
            isLoginButtonEnabled = isLoginButtonEnabled,
            passwordError = passwordError,
        )

        val newStateRenderer = StateRenderer.ScreenContent<LoginState, LoginUIModel>(loginState)
        _stateRendererMutableState.value = newStateRenderer
    }

    private fun togglePasswordVisibility() {
        val visible = loginState.isPasswordVisible
        updateState { copy(isPasswordVisible = !visible) }
    }

    private fun toggleRememberMe() {
        val checked = loginState.isRememberMeChecked
        updateState { copy(isRememberMeChecked = !checked) }
    }

    private fun loginWithUserNameAndPassword(username: String, password: String) = viewModelScope.launch{
        _stateRendererMutableState.update {
            StateRenderer.LoadingScreen(loginState)
        }
        loginUseCase.execute(
            input = LoginUseCase.Input(
                username = username,
                password = password,
            ),
            success = {response->
                _stateRendererMutableState.value= StateRenderer.Success(loginState.loginUIModel)
            },
            error = {errorMessage ->
                _stateRendererMutableState.value= StateRenderer.ErrorPopup(loginState,errorMessage=errorMessage)
            }
        )
    }

}