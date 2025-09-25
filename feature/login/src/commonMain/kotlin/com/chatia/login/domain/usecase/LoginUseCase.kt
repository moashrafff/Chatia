package com.chatia.login.domain.usecase

import com.chatia.login.domain.repo.LoginRepository
import com.chatia.domain.usecase

class LoginUseCase constructor(private val loginRemoteRepo: LoginRepository) :
    AsyncUseCase<LoginUseCase.Input, LoginRe>() {
    data class Input(val username: String, val password: String, val posNumber: String)

    override suspend fun run(input: Input): Result<LoginModel> =
        loginRemoteRepo.login(userName = input.username, password = input.password, posNumber = input.posNumber)
}
