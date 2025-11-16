package com.chatia.register.domain.repo

import com.chatia.domain.result.Result
import com.chatia.register.domain.model.User

interface RegisterRepo {
    suspend fun register(username: String, password: String,
                         email:String,
                         phoneNumber:String): Result<User>
}