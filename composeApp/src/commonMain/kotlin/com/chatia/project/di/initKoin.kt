package com.chatia.project.di

import com.chatia.data.di.dataModule
import com.chatia.login.di.loginModule
import com.chatia.navigator.di.navigationModule
import com.chatia.onBoarding.di.onBoardingModule
import com.chatia.register.di.registerModule
import com.chatia.presentation.di.presentationModule
import org.koin.dsl.KoinAppDeclaration
import org.koin.core.context.startKoin

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(presentationModule, dataModule, navigationModule, onBoardingModule, registerModule, loginModule)
    }
}