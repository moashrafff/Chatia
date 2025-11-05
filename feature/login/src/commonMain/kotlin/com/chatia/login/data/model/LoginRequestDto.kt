package com.chatia.login.data.model

import com.chatia.data.service.RequestDto
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(
    val password: String,
    val userName: String
): RequestDto