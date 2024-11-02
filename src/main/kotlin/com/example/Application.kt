package com.example

import com.example.application.plugins.configureMonitoring
import com.example.application.plugins.configureSerialization
import com.example.di.appModule
import io.ktor.server.application.*
import org.koin.core.module.Module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module(koinModules: List<Module> = listOf(appModule)) {

    install(Koin) {
        slf4jLogger()
        modules(koinModules)
    }


//    configureSecurity(config = tokenConfig)
//    configureRouting(hashService = hashService, userRepositoryImpl = userRepositoryImpl, tokenService = tokenService, tokenConfig = tokenConfig)
    configureSerialization()
    configureMonitoring()
//    configureAdministration()
//    configureSockets()
//    configureTemplating()
//    configureHTTP()
}
