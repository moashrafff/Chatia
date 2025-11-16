package com.chatia.register.domain.model

/**
 * Domain model representing a user after successful registration.
 * This is a pure domain model without any framework dependencies.
 */
data class User(
    val id: Int,
    val userName: String,
    val email: String,
    val phoneNumber: String
)

