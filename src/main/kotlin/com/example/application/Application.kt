package com.example.application

import com.example.application.plugins.*
import com.example.security.hash.SHA256HashService
import com.example.security.token.JwtTokenService
import com.example.security.token.TokenConfig
import io.ktor.server.application.*
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {

    val mongoDbName = "physio-booking"
    val mongoPassword = System.getenv("MONGO_PASSWORD")
    val mongoDb = KMongo.createClient(
        connectionString = "mongodb+srv://usuario_kpb7:$mongoPassword@cluster0.y0fynrk.mongodb.net/pb-auth?retryWrites=true&w=majority&appName=Cluster0"
    ).coroutine
        .getDatabase(name = mongoDbName)

    val tokenService = JwtTokenService()
    val tokenConfig = TokenConfig(
        issuer = environment.config.property("jwt.issuer").getString(),
        audience = environment.config.property("jwt.audience").getString(),
        expiresIn = 365L * 1000L * 60L * 60L * 24L, // 1 año en milisegundos
        secret = System.getenv("JWT_SECRET")
    )
    val hashingService = SHA256HashService()

    configureRouting()
    configureSerialization()
    configureMonitoring()
    configureSecurity(config = tokenConfig)
//    configureAdministration()
//    configureSockets()
//    configureTemplating()
//    configureHTTP()
}
