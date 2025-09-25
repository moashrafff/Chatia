package com.chatia.login.domain.repo

import com.chatia.login.domain.model.LoginResponse

interface LoginRepository {
    suspend fun login(username: String, password: String): Result<LoginResponse>
}