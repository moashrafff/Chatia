package com.chatia.data.source

import com.chatia.data.response.ErrorResponse
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.http.Headers
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.isActive
import com.chatia.domain.model.ErrorMessage

class NetworkDataSource<SERVICE>(
    private val service: SERVICE,
//    private val gson: Gson,
) {
    suspend fun <R, T> performRequest(
        request: suspend SERVICE.() -> HttpResponse<R>,
        onSuccess: suspend (R, Headers) -> Result<T> = { _, _ -> Result.empty() },
        onEmpty: suspend () -> Result<T> = { Result.empty() },
        onError: suspend (ErrorResponse, Int) -> Result<T> = { errorResponse, code ->
            Result.error(
                errorResponse.toDomain(code),
            )
        },
    ): Result<T> {
        try {
            val response:HttpResponse = service.request()

            val responseCode = response.code()
            val errorBody = response.errorBody()?.string()
            if (response.isSuccessful) {
                val body = response.body()
                return if (body != null && body != Unit) {
                    if (coroutineContext.isActive) {
                        onSuccess(body, response.headers())
                    } else {
                        onEmpty()
                    }
                } else {
                    // its success but body equal to null or its empty "Unit"
                    onEmpty()
                }
            } else if (errorBody.isNullOrBlank()) {
                return onError(getDefaultErrorResponse(), responseCode)
            } else {
                //todo
//                Log.d("Aaaaaaaaa",errorBody)
                return onError(getDefaultErrorResponse(), responseCode)
//                return onError(getErrorResponse(gson, errorBody), responseCode)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            val code = when (e) {
                is SocketTimeoutException -> {
//                    TIMEOUT todo
                    400
                }

//        is UnknownHostException, NoConnectivityException -> {
//          NO_INTERNET
//        }

                else -> {
                    //todo
                    401
//                    UNKNOWN
                }
            }
            return onError(getDefaultErrorResponse(), code)
        }
    }
}

// mapping errorResponse to ErrorMessage model
fun ErrorResponse.toDomain(code: Int): ErrorMessage =
    ErrorMessage(
        code = code,
        message = errorMessage,
    )

// create default error response
fun getDefaultErrorResponse() = ErrorResponse("", "")

// getting error response from error body "string"
//fun getErrorResponse(gson: Gson, errorBodyString: String): ErrorResponse =
//    try {
//        gson.fromJson(errorBodyString, ErrorResponse::class.java)
//    } catch (e: Exception) {
//        getDefaultErrorResponse()
//    }
