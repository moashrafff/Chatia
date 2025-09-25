package com.chatia.login.di

import com.chatia.login.data.repoImpl.LoginRepositoryImpl
import com.chatia.login.domain.repo.LoginRepository
import com.chatia.login.presentation.viewmodel.LoginViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    viewModel {
        LoginViewModel()
    }

    //repos
    singleOf<LoginRepository>(::LoginRepositoryImpl)

}