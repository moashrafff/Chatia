package com.chatia.project.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val id: String,
    val userName:String,
    val token:String
)