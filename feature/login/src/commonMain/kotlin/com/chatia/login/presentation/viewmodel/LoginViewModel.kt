package com.chatia.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chatia.login.presentation.error.LoginUIError
import com.chatia.login.presentation.model.LoginUIModel
import com.chatia.login.presentation.protocol.LoginEffect
import com.chatia.login.presentation.protocol.LoginEffect.*
import com.chatia.login.presentation.protocol.LoginIntent
import com.chatia.login.presentation.protocol.LoginState
import com.chatia.login.presentation.validation.LoginValidator
import com.chatia.presentation.stateRenderer.StateRenderer
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

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
            is LoginIntent.LoginClicked -> loginWithUserNameAndPassword(
                username = loginState.loginUIModel.userName,
                password = loginState.loginUIModel.password
            )

            is LoginIntent.LoginWithAppleClicked -> loginWithApple()
            is LoginIntent.LoginWithGoogleClicked -> loginWithGoogle()
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

    private fun loginWithGoogle() {

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

    private fun loginWithUserNameAndPassword(username: String, password: String) {

    }

}