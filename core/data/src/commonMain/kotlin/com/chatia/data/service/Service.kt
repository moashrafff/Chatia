package com.chatia.data.service

import io.ktor.client.statement.HttpResponse

interface Service {
    suspend fun request(requestDto:RequestDto): HttpResponse
}
interface RequestDto