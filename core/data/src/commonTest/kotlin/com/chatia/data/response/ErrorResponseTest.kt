package com.chatia.data.response

import com.chatia.domain.model.ErrorMessage
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ErrorResponseTest {

    @Test
    fun `toDomain should map ErrorResponse to ErrorMessage correctly`() {
        // Given
        val errorResponse = ErrorResponse(
            errorCode = 404,
            errorMessage = "User not found"
        )

        // When
        val result = errorResponse.toDomain()

        // Then
        assertNotNull(result)
        assertEquals(errorResponse.errorCode, result.code)
        assertEquals(errorResponse.errorMessage, result.message)
    }


    @Test
    fun `toDomain should handle empty error message`() {
        // Given
        val errorResponse = ErrorResponse(
            errorCode = 400,
            errorMessage = ""
        )

        // When
        val result = errorResponse.toDomain()

        // Then
        assertEquals(errorResponse.errorCode, result.code)
        assertEquals(errorResponse.errorMessage, result.message)
    }

    @Test
    fun `getDefaultErrorResponse should return ErrorResponse with default values`() {
        // When
        val result = getDefaultErrorResponse().toDomain()

        // Then
        assertNotNull(result)
        assertEquals(-1, result.code)
        assertEquals("", result.message)
    }

    @Test
    fun `toDomain should handle special characters in error message`() {
        // Given
        val errorResponse = ErrorResponse(
            errorCode = 422,
            errorMessage = "Invalid input: \"username\" cannot be empty!"
        )

        // When
        val result = errorResponse.toDomain()

        // Then
        assertEquals(errorResponse.errorCode, result.code)
        assertEquals(errorResponse.errorMessage, result.message)
    }
}

