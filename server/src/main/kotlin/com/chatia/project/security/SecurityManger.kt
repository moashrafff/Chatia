package com.chatia.project.security

interface SecurityManger{
    fun hashPassword(password: String): String
    fun verifyPassword(plainPassword: String,hashedPassword: String): Boolean
}
