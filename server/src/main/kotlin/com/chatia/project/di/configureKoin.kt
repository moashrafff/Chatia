package com.chatia.project.di

import com.chatia.project.data.repoImpl.UserRepositoryImpl
import com.chatia.project.data.serviceImpl.UserServiceImpl
import com.chatia.project.domain.repo.UserRepository
import com.chatia.project.domain.service.UserService
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.application.install
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.ktor.plugin.koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(plugin = Koin, configure = {
        slf4jLogger()
        modules(listOf(reposModule,ServicesModule))
    })
}
val reposModule = module{
    single<UserRepository> {
        UserRepositoryImpl()
    }
}
val ServicesModule = module{
    single<UserService>{
        UserServiceImpl(get())
    }
}