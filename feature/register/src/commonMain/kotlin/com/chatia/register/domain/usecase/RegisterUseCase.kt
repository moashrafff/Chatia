package com.chatia.register.domain.usecase

import com.chatia.domain.result.Result
import com.chatia.domain.usecase.AsyncUseCase
import com.chatia.register.domain.model.User
import com.chatia.register.domain.repo.RegisterRepo

class RegisterUseCase(
    private val registerRepo: RegisterRepo
) : AsyncUseCase<RegisterUseCase.Input, User>() {
    data class Input(
        val username: String,
        val password: String,
        val email: String,
        val phoneNumber: String,
    )

    override suspend fun run(input: Input): Result<User> =
        registerRepo.register(
            username = input.username,
            password = input.password,
            email = input.email,
            phoneNumber = input.phoneNumber
        )
}
