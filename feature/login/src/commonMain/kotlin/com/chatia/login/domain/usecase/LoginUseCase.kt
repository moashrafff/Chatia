package com.chatia.login.domain.usecase

import com.chatia.domain.result.Result
import com.chatia.domain.usecase.AsyncUseCase
import com.chatia.login.data.model.LoginResponseDto
import com.chatia.login.domain.repo.LoginRepository

class LoginUseCase (private val loginRemoteRepo: LoginRepository) :
    AsyncUseCase<LoginUseCase.Input, LoginResponseDto>() {
    data class Input(val username: String, val password: String)

    override suspend fun run(input: Input): Result<LoginResponseDto> =
        loginRemoteRepo.login(username = input.username, password = input.password)
}
