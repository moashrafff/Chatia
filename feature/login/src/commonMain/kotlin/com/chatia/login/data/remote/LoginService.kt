package com.chatia.login.data.remote

import com.chatia.data.service.Ktor
import com.chatia.data.service.RequestDto
import com.chatia.data.service.Service
import com.chatia.login.data.model.LoginRequestDto
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

interface LoginService : Service

class LoginServiceImpl : LoginService {

    override suspend fun request(requestDto: RequestDto): HttpResponse =
        Ktor.client.post(
            Ktor.LOGIN) {
            contentType(ContentType.Application.Json)
            setBody(requestDto as LoginRequestDto)
        }.body()
}




