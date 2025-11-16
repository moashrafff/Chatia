package com.chatia.register.data.mapper

import com.chatia.data.mapper.Mapper
import com.chatia.register.data.model.RegisterResponseDto
import com.chatia.register.domain.model.User

/**
 * Implementation of Mapper for converting RegisterResponseDto (data layer) to User (domain layer).
 */
class RegisterDtoMapper : Mapper<RegisterResponseDto, User> {
    override fun toDomain(from: RegisterResponseDto): User {
        return User(
            id = from.id,
            userName = from.userName,
            email = from.email,
            phoneNumber = from.phoneNumber
        )
    }
}

