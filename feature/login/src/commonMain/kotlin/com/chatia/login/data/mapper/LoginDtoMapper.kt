package com.chatia.login.data.mapper

import com.chatia.data.mapper.Mapper
import com.chatia.login.data.model.LoginResponseDto
import com.chatia.login.domain.model.User

/**
 * Implementation of Mapper for converting LoginResponseDto (data layer) to User (domain layer).
 */
class LoginDtoMapper : Mapper<LoginResponseDto, User> {
    override fun toDomain(from: LoginResponseDto): User {
        return User(
            id = from.id,
            userName = from.userName,
            token = from.token
        )
    }
}

