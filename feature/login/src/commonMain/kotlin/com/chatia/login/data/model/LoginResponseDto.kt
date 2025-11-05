package com.chatia.login.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    val id: String,
    val userName: String,
    val token: String,
)