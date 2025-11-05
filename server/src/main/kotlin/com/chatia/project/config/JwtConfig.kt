package com.chatia.project.config

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.chatia.project.domain.model.User
import io.ktor.server.application.Application
import java.util.Date

fun Application.generateToken(user: User): String?{
    return try{

        val jwtSecret= System.getenv("jwtSecret")
        val issuer=environment.config.property("jwt.issuer").getString()
        val audience=environment.config.property("jwt.audience").getString()
        val claimField=environment.config.property("jwt.claimField").getString()
        val exTime=24*60*60*1000

        return JWT.create()
            .withIssuer(issuer)
            .withAudience(audience)
            .withClaim(claimField,user.id)
            .withExpiresAt(Date(System.currentTimeMillis() + exTime))
            .sign(
                Algorithm.HMAC256(jwtSecret)
            )
    }catch (e: Exception){
        e.printStackTrace()
        null
    }
}

fun Application.jwtVerifier(): JWTVerifier {
    val jwtSecret= System.getenv("jwtSecret")
    val issuer=environment.config.property("jwt.issuer").getString()
    val audience=environment.config.property("jwt.audience").getString()

    return JWT.require(
        Algorithm.HMAC256(jwtSecret))
        .withIssuer(issuer)
        .withAudience(audience)
        .build()
}