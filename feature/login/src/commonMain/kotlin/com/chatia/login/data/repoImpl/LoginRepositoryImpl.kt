package com.chatia.login.data.repoImpl

import com.chatia.data.mapper.Mapper
import com.chatia.data.response.toDomain
import com.chatia.data.source.NetworkDataSource
import com.chatia.domain.result.Result
import com.chatia.login.data.model.LoginRequestDto
import com.chatia.login.data.model.LoginResponseDto
import com.chatia.login.data.remote.LoginService
import com.chatia.login.domain.model.User
import com.chatia.login.domain.repo.LoginRepository

class LoginRepositoryImpl(
    private val networkDataSource: NetworkDataSource<LoginService>,
    private val loginDtoMapper: Mapper<LoginResponseDto, User>
) : LoginRepository {
    override suspend fun login(
        username: String,
        password: String
    ): Result<User> = networkDataSource.performRequest<LoginResponseDto, User>(
        request = {
            request(
                LoginRequestDto(
                    password = password,
                    userName = username
                )
            )
        },
        onSuccess = { response, _ ->
            Result.success(loginDtoMapper.toDomain(response))
        },
        onError = { errorResponse ->
            Result.error(errorResponse.toDomain())
        },
        onEmpty = {
            Result.empty()
        }
    )
}
