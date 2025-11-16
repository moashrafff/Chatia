package com.chatia.project.domain.repo

import com.chatia.project.domain.model.LoginRequest
import com.chatia.project.domain.model.RegisterRequest
import com.chatia.project.domain.model.User

interface UserRepository {
    suspend fun createUser(request: RegisterRequest): User?
    suspend fun authenticate(request: LoginRequest): User?

    suspend fun getUserById(id: Int): User?
}