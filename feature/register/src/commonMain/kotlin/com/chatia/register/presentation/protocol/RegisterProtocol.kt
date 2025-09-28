package com.chatia.register.presentation.protocol

import com.chatia.register.presentation.error.RegisterUIError
import com.chatia.register.presentation.model.RegisterUIModel

data class RegisterUiState(
    val registerUIModel: RegisterUIModel = RegisterUIModel(
        userName = "",
        email = "",
        phone = "",
        password = "",
        confirmPassword = ""
    ),
    val passwordError: RegisterUIError = RegisterUIError.NoError,
    val confirmPasswordError: RegisterUIError = RegisterUIError.NoError,
    val emailError: RegisterUIError = RegisterUIError.NoError,
    val phoneError: RegisterUIError = RegisterUIError.NoError,
    val isRegisterButtonEnabled: Boolean = false
) {
    fun showPasswordError() =
        passwordError != RegisterUIError.NoError && passwordError != RegisterUIError.NoEntry

    fun showConfirmPasswordError() =
        confirmPasswordError != RegisterUIError.NoError && confirmPasswordError != RegisterUIError.NoEntry

    fun showEmailError() =
        emailError != RegisterUIError.NoError && emailError != RegisterUIError.NoEntry

    fun showPhoneError() =
        phoneError != RegisterUIError.NoError && phoneError != RegisterUIError.NoEntry
}

sealed interface RegisterIntent {
    data class UserNameUpdated(val userName: String) : RegisterIntent
    data class PasswordUpdated(val password: String) : RegisterIntent
    data class ConfirmPasswordUpdated(val confirmPassword: String) : RegisterIntent
    data class EmailUpdated(val email: String) : RegisterIntent
    data class PhoneUpdated(val phone: String) : RegisterIntent
    data object RegisterButtonClicked : RegisterIntent
    data object AlreadyHaveAccountClicked: RegisterIntent
}

sealed interface RegisterEffect {
    data object NavigateToHome : RegisterEffect
    data object NavigateToLogin : RegisterEffect
}