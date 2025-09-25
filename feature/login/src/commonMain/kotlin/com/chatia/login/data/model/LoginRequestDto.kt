package com.chatia.login.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(
    val password: String,
    val userName: String
)