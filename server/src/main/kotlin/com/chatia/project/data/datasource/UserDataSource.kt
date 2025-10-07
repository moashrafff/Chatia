package com.chatia.project.data.datasource

import com.chatia.project.domain.model.RegisterRequest
import com.chatia.project.domain.model.User

interface UserDataSource{
    suspend fun findUserByUsername(userName: String): User?
    suspend fun createUser(request: RegisterRequest): User?
    suspend fun getUserById(id: Int): User?
}