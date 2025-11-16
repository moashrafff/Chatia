package com.chatia.login.presentation.error

import cahatia.feature.login.generated.resources.Res
import cahatia.feature.login.generated.resources.empty_text
import cahatia.feature.login.generated.resources.incorrect_password
import cahatia.feature.login.generated.resources.incorrect_password_length
import org.jetbrains.compose.resources.StringResource

sealed class LoginUIError : Error() {
    abstract fun getErrorMessage(): StringResource

    data object NoEntry : LoginUIError() {
        override fun getErrorMessage(): StringResource = Res.string.empty_text
    }

    data object NoError : LoginUIError() {
        override fun getErrorMessage(): StringResource = Res.string.empty_text
    }

    data object InCorrectPassword : LoginUIError() {
        override fun getErrorMessage(): StringResource = Res.string.incorrect_password
    }

    data object InCorrectPasswordLength : LoginUIError() {
        override fun getErrorMessage(): StringResource = Res.string.incorrect_password_length
    }
}