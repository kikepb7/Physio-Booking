package com.example.application.plugins

import com.example.database.DbConnection
import com.example.features.user.data.UserRepositoryImpl
import com.example.features.user.presentation.userRoutes
import io.ktor.server.application.*

fun Application.configureRouting(
    userRepositoryImpl: UserRepositoryImpl
) {

    DbConnection.getDatabaseInstance()

    userRoutes(userRepositoryImpl = userRepositoryImpl)
}
