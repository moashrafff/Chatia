package com.chatia.register.di

import com.chatia.register.presentation.viewmodel.RegisterViewmodel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val registerModule = module {
    viewModel {
        RegisterViewmodel()
    }
}