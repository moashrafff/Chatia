package com.chatia.project.data.serviceImpl

import com.chatia.project.domain.model.LoginRequest
import com.chatia.project.domain.model.RegisterRequest
import com.chatia.project.domain.model.User
import com.chatia.project.domain.repo.UserRepository
import com.chatia.project.domain.service.UserService

class UserServiceImpl(
    private val userRepository: UserRepository
): UserService
{
    override suspend fun registerUser(registerRequest: RegisterRequest): User?
            = userRepository.createUser(registerRequest)

    override suspend fun loginUser(loginRequest: LoginRequest): User?
            = userRepository.authenticate(loginRequest)

    override suspend fun getUserById(id: Int): User?
            = userRepository.getUserById(id)
}