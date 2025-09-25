package com.chatia.login.data.remote


import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json

object Ktor {
    private const val BASE_URL = "http://localhost:8080/chatia/"
    val client: HttpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
        defaultRequest {
            url(BASE_URL)
        }
    }
    //end points
    const val LOGIN="users/login"


}