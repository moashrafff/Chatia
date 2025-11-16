package com.chatia.data.di

import com.chatia.data.service.Ktor
import org.koin.dsl.module

val dataModule = module {
    single { Ktor.client }
}
