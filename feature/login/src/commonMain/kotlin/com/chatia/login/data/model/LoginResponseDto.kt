package com.chatia.login.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    val id: String,
    val token: String,
    val userName: String
)