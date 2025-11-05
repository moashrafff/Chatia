package com.chatia.navigator.di

import com.chatia.navigator.core.AppNavigator
import com.chatia.navigator.core.AppNavigatorImpl
import com.chatia.navigator.viewmodel.AppNavigatorViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val navigationModule = module {
    single<AppNavigator> { AppNavigatorImpl() }
    viewModel {
        AppNavigatorViewModel(get())
    }
}