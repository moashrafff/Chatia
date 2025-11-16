package com.chatia.data.service


import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object Ktor {
    private const val BASE_URL = "http://10.0.2.2:8080/chatia/"
    val client: HttpClient = HttpClient {
        install(ContentNegotiation) {
            json()
            Json {
                ignoreUnknownKeys = true
            }
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
    const val REGISTER="users/register"


}