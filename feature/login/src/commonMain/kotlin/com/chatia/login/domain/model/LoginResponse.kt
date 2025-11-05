package com.chatia.login.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val id: String,
    val token: String,
    val userName: String
)