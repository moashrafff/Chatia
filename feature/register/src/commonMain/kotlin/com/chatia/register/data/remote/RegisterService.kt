package com.chatia.register.data.remote

import com.chatia.data.service.Ktor
import com.chatia.data.service.RequestDto
import com.chatia.data.service.Service
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

class RegisterServiceImpl : RegisterService {
    override suspend fun request(requestDto: RequestDto): HttpResponse =
        Ktor.client.post(
            Ktor.REGISTER) {
            contentType(ContentType.Application.Json)
            setBody(requestDto)
        }.body()
}

interface RegisterService : Service