package com.example.application.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*

fun Application.configureSecurity() {

    authentication {
//        jwt {
//            realm = this@configureSecurity.environment.config.property("jwt.realm").getString()
//            verifier(
//                JWT
//                    .require(Algorithm.HMAC256(config.secret))
//                    .withAnyOfAudience(config.audience)
//                    .withIssuer(config.issuer)
//                    .build()
//            )
//            validate { credential ->
//                if (credential.payload.audience.contains(config.audience)) JWTPrincipal(credential.payload) else null
//            }
//        }
    }
}