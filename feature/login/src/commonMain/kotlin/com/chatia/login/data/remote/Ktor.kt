package com.chatia.login.data.remote


import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json

object Ktor {
    private const val BASE_URL = "http://10.0.2.2:8080/chatia/"
    val client: HttpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
        defaultRequest {
            url(BASE_URL)
        }
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
    }

    //end points
    const val LOGIN="users/login"


}