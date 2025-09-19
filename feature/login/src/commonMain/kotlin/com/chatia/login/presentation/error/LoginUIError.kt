package com.chatia.login.presentation.error

import com.chatia.presentation.models.UiText

sealed class LoginUIError : Error() {
    abstract fun getErrorMessage(): UiText

    data object NoEntry : LoginUIError() {
        override fun getErrorMessage(): UiText = UiText.Dynamic("")
    }

    data object NoError : LoginUIError() {
        override fun getErrorMessage(): UiText = UiText.Dynamic("")
    }

    data object InCorrectPassword : LoginUIError() {
        override fun getErrorMessage(): UiText = UiText.Dynamic("")
    }

    data object InCorrectPasswordLength : LoginUIError() {
        override fun getErrorMessage(): UiText = UiText.Dynamic("")
    }
}