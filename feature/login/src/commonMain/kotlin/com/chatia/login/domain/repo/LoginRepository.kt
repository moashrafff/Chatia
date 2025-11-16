package com.chatia.login.domain.repo

import com.chatia.domain.result.Result
import com.chatia.login.data.model.LoginResponseDto
import com.chatia.login.domain.model.User

interface LoginRepository {
    suspend fun login(username: String, password: String): Result<User>
}