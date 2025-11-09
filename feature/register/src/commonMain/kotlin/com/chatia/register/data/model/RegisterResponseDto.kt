package com.chatia.register.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponseDto(
    val id:Int,
    val userName:String,
    val email:String,
    val phoneNumber:String,
    val password:String//hashed
)
