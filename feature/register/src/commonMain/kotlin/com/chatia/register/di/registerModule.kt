package com.chatia.register.di

import com.chatia.data.mapper.Mapper
import com.chatia.data.source.NetworkDataSource
import com.chatia.register.data.mapper.RegisterDtoMapper
import com.chatia.register.data.model.RegisterResponseDto
import com.chatia.register.data.remote.RegisterService
import com.chatia.register.data.remote.RegisterServiceImpl
import com.chatia.register.data.repoImpl.RegisterRepoImpl
import com.chatia.register.domain.model.User
import com.chatia.register.domain.repo.RegisterRepo
import com.chatia.register.domain.usecase.RegisterUseCase
import com.chatia.register.presentation.viewmodel.RegisterViewmodel
import org.koin.core.qualifier.named
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

private val REGISTER_DATA_SOURCE = named("REGISTER_DATA_SOURCE")
private val REGISTER_MAPPER = named("REGISTER_MAPPER")

val registerModule = module {

    // Mappers
    single<Mapper<RegisterResponseDto, User>>(REGISTER_MAPPER) { RegisterDtoMapper() }

    // Services
    singleOf(::RegisterServiceImpl) bind RegisterService::class

    //data source
    factory<NetworkDataSource<RegisterService>>(REGISTER_DATA_SOURCE) { 
        NetworkDataSource(get<RegisterService>()) 
    }

    // Repositories
    single<RegisterRepo> {
        RegisterRepoImpl(
            networkDataSource = get<NetworkDataSource<RegisterService>>(REGISTER_DATA_SOURCE),
            registerDtoMapper = get<Mapper<RegisterResponseDto, User>>(REGISTER_MAPPER)
        )
    }

    //use cases
    singleOf(::RegisterUseCase)

    //viewmodel
    viewModel {
        RegisterViewmodel(
            get<RegisterUseCase>(),
        )
    }
}