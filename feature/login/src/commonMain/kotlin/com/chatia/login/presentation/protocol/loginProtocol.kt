package com.chatia.login.presentation.protocol

import com.chatia.domain.model.ErrorMessage
import com.chatia.login.presentation.error.LoginUIError
import com.chatia.login.presentation.model.LoginUIModel
import com.mmk.kmpauth.google.GoogleUser

sealed interface LoginIntent {
    data class UserNameUpdated(val userName: String) : LoginIntent
    data class PasswordUpdated(val password: String) : LoginIntent
    data object RememberMeClicked : LoginIntent
    data object PasswordVisibleClicked : LoginIntent
    data object ForgetPasswordClicked : LoginIntent
    data object OnLoginClicked : LoginIntent
    data object OnFacebookLoginClicked : LoginIntent
    data class LoginWithGoogleClickedResult(val googleUser: GoogleUser?) : LoginIntent
    data object LoginWithAppleClicked : LoginIntent
    data object CreateAccountClicked : LoginIntent
}

sealed interface LoginEffect {
    data class NavigateToHome(val userName: String) : LoginEffect
    data object NavigateToRegister : LoginEffect
    data class NavigateToForgetPassword(val userName: String) : LoginEffect
    data class ShowError(val errorMessage: ErrorMessage) : LoginEffect
}

data class LoginState(
    val loginUIModel: LoginUIModel = LoginUIModel(
        userName = "",
        password = "",
        ),
    val isLoginButtonEnabled: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val isRememberMeChecked: Boolean = false,
    val passwordError: LoginUIError = LoginUIError.NoEntry,
){
    fun showPasswordError() =
        passwordError != LoginUIError.NoError && passwordError != LoginUIError.NoEntry
}
