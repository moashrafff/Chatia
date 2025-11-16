package com.chatia.login.domain.model

/**
 * Domain model representing a user after successful authentication.
 * This is a pure domain model without any framework dependencies.
 */
data class User(
    val id: String,
    val userName: String,
    val token: String
)

