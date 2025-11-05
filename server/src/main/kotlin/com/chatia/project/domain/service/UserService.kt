package com.chatia.project.domain.service

import com.chatia.project.domain.model.LoginRequest
import com.chatia.project.domain.model.RegisterRequest
import com.chatia.project.domain.model.User

interface UserService {
    suspend fun registerUser(registerRequest: RegisterRequest): User?
    suspend fun loginUser(loginRequest: LoginRequest): User?
    suspend fun getUserById(id: Int): User?
}