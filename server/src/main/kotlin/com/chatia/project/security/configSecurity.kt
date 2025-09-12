package com.chatia.project.security

import com.chatia.project.config.jwtVerifier
import com.chatia.project.domain.service.UserService
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.jwt

fun Application.configSecurity(
    userService: UserService
){

    val jwtSecret= System.getenv("jwtSecret")
    val jwtIssuer=environment.config.property("jwt.issuer").getString()
    val jwtAudience=environment.config.property("jwt.audience").getString()
    val jwtClaimField=environment.config.property("jwt.claimField").getString()
    val jwtRealm=environment.config.property("jwt.realm").getString()

    val jwtVerifier=jwtVerifier()

    install(Authentication){
        jwt("auth-jwt") {
            realm=jwtRealm
            verifier(jwtVerifier)
            validate { cred->
                cred.payload.getClaim(
                    jwtClaimField
                ).asInt()?.let {
                    userService.getUserById(it)
                }
            }
        }
    }
}