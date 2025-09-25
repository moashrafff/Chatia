package com.chatia.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    @SerialName("status")
    val errorCode: String,
    @SerialName("message")
    val errorMessage: String,
)
