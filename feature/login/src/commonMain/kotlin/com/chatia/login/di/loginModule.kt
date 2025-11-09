package com.chatia.login.di

import com.chatia.data.mapper.Mapper
import com.chatia.data.source.NetworkDataSource
import com.chatia.login.data.mapper.LoginDtoMapper
import com.chatia.login.data.model.LoginResponseDto
import com.chatia.login.data.remote.LoginService
import com.chatia.login.data.remote.LoginServiceImpl
import com.chatia.login.data.repoImpl.LoginRepositoryImpl
import com.chatia.login.domain.model.User
import com.chatia.login.domain.repo.LoginRepository
import com.chatia.login.domain.usecase.LoginUseCase
import com.chatia.login.presentation.viewmodel.LoginViewModel
import org.koin.core.qualifier.named
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

private val LOGIN_DATA_SOURCE = named("LOGIN_DATA_SOURCE")
private val LOGIN_MAPPER = named("LOGIN_MAPPER")

val loginModule = module {

    // Mappers
    single<Mapper<LoginResponseDto, User>>(LOGIN_MAPPER) { LoginDtoMapper() }

    // Services
    singleOf(::LoginServiceImpl) bind LoginService::class

    //data source
    factory<NetworkDataSource<LoginService>>(LOGIN_DATA_SOURCE) { 
        NetworkDataSource(get<LoginService>()) 
    }

    // Repositories
    single<LoginRepository> {
        LoginRepositoryImpl(
            networkDataSource = get<NetworkDataSource<LoginService>>(LOGIN_DATA_SOURCE),
            loginDtoMapper = get<Mapper<LoginResponseDto, User>>(LOGIN_MAPPER)
        )
    }

    //use cases
    singleOf(::LoginUseCase)

    //viewmodel
    viewModel {
        LoginViewModel(
            get<LoginUseCase>(),
        )
    }

}