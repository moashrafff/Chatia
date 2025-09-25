package com.chatia.login.data.remote

import com.chatia.login.data.model.LoginRequestDto
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.buildUrl
import io.ktor.utils.io.InternalAPI

class LoginService {

    @OptIn(InternalAPI::class)
    suspend fun login(username: String, password: String): HttpResponse =
        Ktor.client.post {
            buildUrl {
                Ktor.LOGIN
            }
            body = LoginRequestDto(password = password, userName = username)
    }
}