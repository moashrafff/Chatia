package com.chatia.project.domain.validator


object UserDataValidator {
    private fun validateEmail(email: String?) =
        if (Validator.EmptyOrNullValidator(email).validate())
            ValidationResults.ALL_FIELDS_REQUIRED
        else if (Validator.RegexValidator(
                email!!,
                Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
            ).validate().not()
        )
            ValidationResults.INVALID_EMAIL_FORMATE
        else ValidationResults.VALID_USER

    private fun validatePassword(password: String?) =
        if (Validator.EmptyOrNullValidator(password).validate())
            ValidationResults.ALL_FIELDS_REQUIRED
        else if (Validator.RegexValidator(
                password!!,
                Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$")
            ).validate().not()
        )
            ValidationResults.INVALID_PASSWORD_FORMATE
        else ValidationResults.VALID_USER

    private fun validateUsername(username: String?) =
        if (Validator.EmptyOrNullValidator(username).validate())
            ValidationResults.ALL_FIELDS_REQUIRED
        else if (Validator.LengthValidator(value = username!!, min = 5, max = null).validate().not())
            ValidationResults.INVALID_USERNAME_LENGTH
        else ValidationResults.VALID_USER

    fun validateUserData(email: String?, password: String?, username: String?): ValidationResults {
        return when {
            validateEmail (email) != ValidationResults.VALID_USER -> validateEmail(email)
            validatePassword(password) != ValidationResults.VALID_USER -> validatePassword(password)
            validateUsername(username) != ValidationResults.VALID_USER -> validateUsername(username)
            else -> ValidationResults.VALID_USER
        }
    }
    fun validateLoginRequest(username: String?,password: String?)=
        when{
            validateUsername(username) != ValidationResults.VALID_USER -> validateUsername(username)
            validatePassword(password) != ValidationResults.VALID_USER -> validatePassword(password)
            else -> ValidationResults.VALID_USER
        }
}


