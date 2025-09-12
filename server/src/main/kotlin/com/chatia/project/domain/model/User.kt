package com.chatia.project.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val userName: String,
    val email: String,
    val phoneNumber: String,
)