package com.chatia.project.routes

import com.chatia.project.config.generateToken
import com.chatia.project.domain.model.LoginRequest
import com.chatia.project.domain.model.LoginResponse
import com.chatia.project.domain.model.RegisterRequest
import com.chatia.project.domain.service.UserService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import kotlinx.serialization.Serializable


fun Application.userRoutes(userService: UserService){
    routing {
        route("/chatia/users"){
            post("/register") {
                val request=call.receive<RegisterRequest>()
                val user=userService.registerUser(request)
                if(user!=null){
                    call.respond(status= HttpStatusCode.Created,message= user)
                }else{
                    call.respond(status=HttpStatusCode.Conflict,message= ErrorMessage(
                        errorCode = HttpStatusCode.Conflict.value, errorMessage = "User Already Exists"
                    )
                    )
                }
            }
            post("/login") {
                val request=call.receive<LoginRequest>()
                val user=userService.loginUser(request)
                if(user!=null){
                    val token=generateToken(user)
                    if(token!=null) {
                        val res = LoginResponse(
                            id = user.id.toString(),
                            userName = user.userName,
                            token = token
                        )
                        call.respond(status = HttpStatusCode.OK, message = res)
                    }else{
                        call.respond(status = HttpStatusCode.Unauthorized, message = ErrorMessage(
                            errorCode = HttpStatusCode.Unauthorized.value, errorMessage = "Invalid Credentials"
                        )
                        )
                    }
                }else{ call.respond(status = HttpStatusCode.Unauthorized, message = ErrorMessage(
                    errorCode = HttpStatusCode.Unauthorized.value, errorMessage = "Invalid Credentials"
                )
                )
                }
            }
        }
    }
}
@Serializable
data class ErrorMessage(
    val errorCode: Int,
    val errorMessage: String
)
