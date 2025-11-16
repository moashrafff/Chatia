package com.chatia.register.data.repoImpl

import com.chatia.data.mapper.Mapper
import com.chatia.data.response.toDomain
import com.chatia.data.source.NetworkDataSource
import com.chatia.domain.result.Result
import com.chatia.register.data.model.RegisterRequestDto
import com.chatia.register.data.model.RegisterResponseDto
import com.chatia.register.data.remote.RegisterService
import com.chatia.register.domain.model.User
import com.chatia.register.domain.repo.RegisterRepo

class RegisterRepoImpl(
    private val networkDataSource: NetworkDataSource<RegisterService>,
    private val registerDtoMapper: Mapper<RegisterResponseDto, User>
) : RegisterRepo {
    override suspend fun register(
        username: String,
        password: String,
        email: String,
        phoneNumber: String
    ): Result<User> = networkDataSource.performRequest<RegisterResponseDto, User>(
        request = {
            request(
                RegisterRequestDto(
                    userName = username,
                    password = password,
                    email = email,
                    phoneNumber = phoneNumber
                )
            )
        },
        onSuccess = { response, _ ->
            Result.success(registerDtoMapper.toDomain(response))
        },
        onError = { errorResponse ->
            Result.error(errorResponse.toDomain())
        },
        onEmpty = {
            Result.empty()
        }
    )
}