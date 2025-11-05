package com.chatia.login.data.repoImpl

import com.chatia.data.response.toDomain
import com.chatia.data.service.Service
import com.chatia.data.source.NetworkDataSource
import com.chatia.login.domain.repo.LoginRepository
import com.chatia.domain.result.Result
import com.chatia.login.data.model.LoginRequestDto
import com.chatia.login.data.model.LoginResponseDto


class LoginRepositoryImpl (
    private val networkDataSource: NetworkDataSource<Service>,
): LoginRepository {
    override suspend fun login(
        username: String,
        password: String
    ): Result<LoginResponseDto> = networkDataSource.performRequest<LoginResponseDto, LoginResponseDto>(
        request = {
            request(
                LoginRequestDto(
                    password = password, userName = username
                )
            )
        },
        onSuccess = { response, headers ->
            Result.success(LoginResponseDto(
                id = response.id,
                token = response.token,
                userName = response.userName
            ))
        },
        onError = { errorResponse ->
            Result.error(errorResponse.toDomain())
        },
        onEmpty = {
            Result.empty()
        }
    )
}
