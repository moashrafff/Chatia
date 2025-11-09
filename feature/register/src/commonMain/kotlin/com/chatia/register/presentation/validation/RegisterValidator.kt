package com.chatia.register.presentation.validation

import com.chatia.register.presentation.error.RegisterUIError

private const val PASSWORD_MIN_LENGTH = 6
private const val PASSWORD_MAX_LENGTH = 10

object RegisterValidator {

    fun emailError(email: String): RegisterUIError = when {
        email.isEmpty() -> RegisterUIError.NoEntry
        !email.isValidEmail() -> RegisterUIError.InvalidEmail
        else -> RegisterUIError.NoError
    }

    fun phoneError(phone: String): RegisterUIError = when {
        phone.isEmpty() -> RegisterUIError.NoEntry
        !phone.matches(Regex("""^\+?[0-9\s\-()]{7,20}$""")) -> RegisterUIError.InvalidPhone
        else -> RegisterUIError.NoError
    }

    fun passwordError(password: String): RegisterUIError = when {
        password.isEmpty() -> RegisterUIError.NoEntry
        !password.isValidPasswordLength() -> RegisterUIError.InvalidPasswordLength
        !password.isAlphaNumericWithSpecialCharacters() -> RegisterUIError.InvalidPassword
        else -> RegisterUIError.NoError
    }

    fun confirmPasswordError(password: String, confirmPassword: String): RegisterUIError = when {
        confirmPassword.isEmpty() -> RegisterUIError.NoEntry
        confirmPassword != password -> RegisterUIError.PasswordsDoNotMatch
        else -> RegisterUIError.NoError
    }

    fun isRegisterValid(
        emailError: RegisterUIError,
        phoneError: RegisterUIError,
        passwordError: RegisterUIError,
        confirmPasswordError: RegisterUIError,
    ): Boolean = listOf(
        emailError, phoneError, passwordError, confirmPasswordError
    ).all { it == RegisterUIError.NoError }


    private fun String.isAlphaNumericWithSpecialCharacters(): Boolean {
        var hasLower = false
        var hasUpper = false
        var hasDigit = false
        var hasSpecial = false
        for (ch in this) {
            when {
                ch.isLowerCase() -> hasLower = true
                ch.isUpperCase() -> hasUpper = true
                ch.isDigit() -> hasDigit = true
                !ch.isLetterOrDigit() -> hasSpecial = true
            }
        }
        return hasLower && hasUpper && hasDigit && hasSpecial
    }

    private fun String.isValidEmail(): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
        return emailRegex.matches(this)
    }

    private fun String.isValidPasswordLength() =
        this.count() in PASSWORD_MIN_LENGTH..PASSWORD_MAX_LENGTH
}