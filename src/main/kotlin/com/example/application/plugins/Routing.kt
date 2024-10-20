package com.example.application.plugins

import com.example.data.datasource.local.DbConnection
import com.example.data.repository.user.UserRepositoryImpl
import com.example.data.services.route.*
import com.example.security.hash.HashService
import com.example.security.token.TokenConfig
import com.example.security.token.TokenService
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting(
    hashService: HashService,
    userRepositoryImpl: UserRepositoryImpl,
    tokenService: TokenService,
    tokenConfig: TokenConfig
) {

    DbConnection.getDatabaseInstance()

    routing {
        signIn(userRepositoryImpl, hashService, tokenService, tokenConfig)
        signUp(userRepositoryImpl, hashService)
        authenticate()
        getSecretInfo()
    }

    userRoutes(userRepositoryImpl = userRepositoryImpl)
}
