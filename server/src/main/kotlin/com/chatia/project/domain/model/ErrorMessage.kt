package com.chatia.project.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ErrorMessage(
    val errorCode: Int,
    val errorMessage: String
)