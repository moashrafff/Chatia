package com.chatia.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ErrorMessage(
    val code: Int,
    val message: String,
)


