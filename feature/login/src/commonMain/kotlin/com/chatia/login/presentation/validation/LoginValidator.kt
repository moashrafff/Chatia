package com.chatia.login.presentation.validation

import com.chatia.login.presentation.error.LoginUIError

private const val PASSWORD_MIN_LENGTH = 6
private const val PASSWORD_MAX_LENGTH = 10

object LoginValidator {
    fun passwordError(password: String): LoginUIError = when {
        password.isEmpty() -> LoginUIError.NoEntry
        !isValidPasswordLength(password) -> LoginUIError.InCorrectPasswordLength
        !password.isAlphaNumericWithSpecialCharacters() -> LoginUIError.InCorrectPassword
        else -> LoginUIError.NoError
    }

    private fun String.isAlphaNumericWithSpecialCharacters(): Boolean {
        val containsLowerCase = any { it.isLowerCase() }
        val containsUpperCase = any { it.isUpperCase() }
        val containsSpecialCharacters = any { !it.isLetterOrDigit() }
        val containsDigits = any { it.isDigit() }
        return containsDigits && containsLowerCase && containsUpperCase && containsSpecialCharacters
    }

    private fun isValidPasswordLength(password: String): Boolean =
        password.count() in PASSWORD_MIN_LENGTH..PASSWORD_MAX_LENGTH

    fun isLoginValid(passwordError: LoginUIError): Boolean =
        (passwordError == LoginUIError.NoError) && (passwordError != LoginUIError.NoEntry)
}