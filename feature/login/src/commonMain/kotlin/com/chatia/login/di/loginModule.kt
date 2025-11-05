package com.chatia.login.di

import com.chatia.data.service.Service
import com.chatia.data.source.NetworkDataSource
import com.chatia.login.data.remote.Ktor
import com.chatia.login.data.remote.LoginServiceImpl
import com.chatia.login.data.repoImpl.LoginRepositoryImpl
import com.chatia.login.domain.repo.LoginRepository
import com.chatia.login.domain.usecase.LoginUseCase
import com.chatia.login.presentation.viewmodel.LoginViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val loginModule = module {

    single { Ktor.client }

    //data source
    factory<NetworkDataSource<Service>> { NetworkDataSource(get()) }

    // Repositories
    singleOf(::LoginRepositoryImpl) bind LoginRepository::class

    // Services
    singleOf(::LoginServiceImpl) bind Service::class

    //use cases
    singleOf(::LoginUseCase)

    //viewmodel
    viewModel {
        LoginViewModel(
            get<LoginUseCase>(),
        )
    }

}