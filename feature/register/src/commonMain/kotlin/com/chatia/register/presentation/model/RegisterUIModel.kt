package com.chatia.register.presentation.model

import com.stevdza_san.library.domain.Country

data class RegisterUIModel(
    val userName: String,
    val email: String,
    val phone: String,
    val password: String,
    val confirmPassword: String,
    val country:Country
)
