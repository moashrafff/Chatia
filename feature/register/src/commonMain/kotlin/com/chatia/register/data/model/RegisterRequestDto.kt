package com.chatia.register.data.model

import com.chatia.data.service.RequestDto
import kotlinx.serialization.Serializable
@Serializable
data class RegisterRequestDto(
   val userName:String,
   val password:String,
   val email:String,
   val phoneNumber:String
): RequestDto
