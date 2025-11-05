package com.chatia.domain.usecase

import com.chatia.domain.model.ErrorMessage
import com.chatia.domain.result.Result


abstract class AsyncUseCase<I, R> : UseCase<R> {
    private lateinit var success: suspend (R) -> Unit
    private lateinit var empty: suspend () -> Unit
    private lateinit var error: suspend (ErrorMessage) -> Unit

    suspend fun execute(
        input: I,
        success: suspend (R) -> Unit = {},
        empty: suspend () -> Unit = {},
        error: suspend (ErrorMessage) -> Unit = {},
    ) {
        this.success = success
        this.empty = empty
        this.error = error

        run(input).perform(this)
    }

    abstract suspend fun run(input: I): Result<R>

    override suspend fun onSuccess(success: Result.Success<R>) {
        success(success.data)
    }

    override suspend fun onEmpty() {
        empty()
    }

    override suspend fun onError(errorMessage: ErrorMessage) {
        error(errorMessage)
    }
}
 