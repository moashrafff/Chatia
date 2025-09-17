package com.chatia.project.chatiaApp

import android.app.Application
import com.chatia.project.di.initKoin
import org.koin.android.ext.koin.androidContext

class ChatiaApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(config = {
            androidContext(this@ChatiaApplication)
        })
    }
}