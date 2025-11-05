package com.chatia.project

import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import com.chatia.project.db.initDB
import com.chatia.project.di.configureKoin
import com.chatia.project.domain.service.UserService
import com.chatia.project.routes.userRoutes
import com.chatia.project.security.configSecurity
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import org.koin.ktor.ext.get


fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
    embeddedServer(Netty,   host = "https://sanofi-nationalday.com", module = Application::module)
        .start(wait = true)
}
fun Application.module() {
    configureKoin()
    initDB()

    configSecurity(
        get<UserService>()
    )
    install(ContentNegotiation) {
        json()
    }
    userRoutes(get<UserService>())
}
