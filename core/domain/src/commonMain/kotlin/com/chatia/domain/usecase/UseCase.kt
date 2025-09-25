package com.chatia.domain.usecase

import com.chatia.domain.model.ErrorMessage
import com.chatia.domain.result.Result
interface UseCase<R> {
    suspend fun onSuccess(success: Result.Success<R>)
    suspend fun onEmpty()
    suspend fun onError(errorMessage: ErrorMessage)
}
