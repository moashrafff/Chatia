package com.chatia.project.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val userName: String,
    val email: String,
    val phoneNumber:String,
    val password: String)
