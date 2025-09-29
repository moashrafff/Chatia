package com.chatia.data.response

import com.chatia.domain.model.ErrorMessage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val errorCode: Int,
    val errorMessage: String,
)


// mapping errorResponse to ErrorMessage model
fun ErrorResponse.toDomain(): ErrorMessage =
    ErrorMessage(
        code = errorCode,
        message = errorMessage,
    )

// create default error response
fun getDefaultErrorResponse() = ErrorResponse(-1, "")