package com.chatia.register.presentation.error

import cahatia.feature.register.generated.resources.Res
import cahatia.feature.register.generated.resources.empty_text
import cahatia.feature.register.generated.resources.register_invalid_email
import cahatia.feature.register.generated.resources.register_invalid_password
import cahatia.feature.register.generated.resources.register_invalid_password_length
import cahatia.feature.register.generated.resources.register_invalid_phone
import cahatia.feature.register.generated.resources.register_passwords_do_not_match
import org.jetbrains.compose.resources.StringResource

sealed class RegisterUIError : Error() {
    abstract fun getErrorMessage(): StringResource

    data object NoEntry : RegisterUIError() {
        override fun getErrorMessage(): StringResource = Res.string.empty_text
    }

    data object NoError : RegisterUIError() {
        override fun getErrorMessage(): StringResource = Res.string.empty_text
    }

    data object InvalidEmail : RegisterUIError() {
        override fun getErrorMessage(): StringResource = Res.string.register_invalid_email
    }

    data object InvalidPhone : RegisterUIError() {
        override fun getErrorMessage(): StringResource = Res.string.register_invalid_phone
    }

    data object InvalidPassword : RegisterUIError() {
        override fun getErrorMessage(): StringResource = Res.string.register_invalid_password
    }

    data object InvalidPasswordLength : RegisterUIError() {
        override fun getErrorMessage(): StringResource = Res.string.register_invalid_password_length
    }

    data object PasswordsDoNotMatch : RegisterUIError() {
        override fun getErrorMessage(): StringResource = Res.string.register_passwords_do_not_match
    }
}