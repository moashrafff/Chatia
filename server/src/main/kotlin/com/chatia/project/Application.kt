package com.chatia.project

import com.chatia.project.db.initDB
import com.chatia.project.di.configureKoin
import com.chatia.project.domain.service.UserService
import com.chatia.project.routes.userRoutes
import com.chatia.project.security.configSecurity
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import org.koin.ktor.ext.get


fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
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
