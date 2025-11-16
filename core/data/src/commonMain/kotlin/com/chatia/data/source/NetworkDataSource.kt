package com.chatia.data.source

import com.chatia.data.response.ErrorResponse
import com.chatia.domain.result.Result
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.Headers
import io.ktor.http.isSuccess
import kotlinx.coroutines.isActive
import kotlinx.serialization.json.Json
import kotlin.coroutines.coroutineContext

class NetworkDataSource<SERVICE>(
    val service: SERVICE,
) {
    suspend inline fun <reified T, reified R> performRequest(
        request: suspend SERVICE.() -> HttpResponse,
        onSuccess: suspend (T, Headers) -> Result<R>,
        onEmpty: suspend () -> Result<R>,
        onError: suspend (ErrorResponse) -> Result<R>
    ): Result<R> {
        try {
            val response: HttpResponse = service.request()
            val responseCode = response.status.value

            if (response.status.isSuccess()) {
                val body = Json.decodeFromString<T>(response.bodyAsText())

                return if (body != null && body != Unit) {
                    if (coroutineContext.isActive) {
                        onSuccess(body, response.headers)
                    } else {
                        onEmpty()
                    }
                } else {
                    // its success but body equal to null or its empty "Unit"
                    onEmpty()
                }
            } else {
                val errorBody = response.body<ErrorResponse>()
                return onError((errorBody).copy(errorCode = responseCode))
            }
        } catch (e: Exception) {
            val error = ErrorResponse(
                errorMessage = e.message ?: "",
                errorCode = when (e) {
                    is SocketTimeoutException -> {
                        TIMEOUT
                    }
                    else -> {
                        UNKNOWN
                    }
                }
            )
            return onError(error)
        }
    }
}

const val TIMEOUT = -2
const val UNKNOWN = -1

