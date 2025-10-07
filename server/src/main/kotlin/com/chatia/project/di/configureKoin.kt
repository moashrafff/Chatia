package com.chatia.project.di

import com.chatia.project.data.datasource.UserDataSource
import com.chatia.project.data.datasource.UserDataSourceImpl
import com.chatia.project.data.repoImpl.UserRepositoryImpl
import com.chatia.project.data.serviceImpl.UserServiceImpl
import com.chatia.project.domain.repo.UserRepository
import com.chatia.project.domain.service.UserService
import com.chatia.project.security.SecurityManger
import com.chatia.project.security.SecurityMangerImpl
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(plugin = Koin, configure = {
        slf4jLogger()
        modules(listOf(reposModule))
    })
}
val reposModule = module{
    single<SecurityManger> {
        SecurityMangerImpl()
    }
    single<UserRepository> {
        UserRepositoryImpl(
            get(),
            get()
        )
    }
    single<UserDataSource> {
        UserDataSourceImpl(get())
    }
    single<UserService>{
        UserServiceImpl(get())
    }
}