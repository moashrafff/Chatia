package com.chatia.register.presentation.model

data class RegisterUIModel(
    val userName: String,
    val email: String,
    val phone: String,
    val password: String,
    val confirmPassword: String
)
