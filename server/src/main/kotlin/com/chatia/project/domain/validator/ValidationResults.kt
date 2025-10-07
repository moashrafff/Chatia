package com.chatia.project.domain.validator

enum class ValidationResults(val message: String) {
    ALL_FIELDS_REQUIRED("All fields must be filled"),
    INVALID_EMAIL_FORMATE("Invalid email format"),
    INVALID_PASSWORD_FORMATE("Password must contain at least one uppercase letter, one lowercase letter, one digit, one special character, and be at least 8 characters long"),
    INVALID_USERNAME_LENGTH("Username must be at least 5 characters long"),
    INVALID_PASSWORD("Invalid password"),
    USER_NOT_FOUND("User not found"),
    VALID_USER("Valid user")
}