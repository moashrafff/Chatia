package com.chatia.project

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun isIOS (): Boolean