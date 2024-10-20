package com.example.application

import com.example.application.plugins.configureMonitoring
import com.example.application.plugins.configureRouting
import com.example.application.plugins.configureSecurity
import com.example.application.plugins.configureSerialization
import com.example.data.repository.user.UserRepositoryImpl
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
    val userRepositoryImpl = UserRepositoryImpl(mongoDb = mongoDb)

//    GlobalScope.launch {
//        val user = UserDto(
//            username = "test",
//            password = "test-password",
//            salt = "salt"
//        )
//        userRepositoryImpl.insertUser(user = user)
//    }

    val tokenService = JwtTokenService()
    val tokenConfig = TokenConfig(
        issuer = environment.config.property("jwt.issuer").getString(),
        audience = environment.config.property("jwt.audience").getString(),
        expiresIn = 365L * 1000L * 60L * 60L * 24L, // 1 año en milisegundos
        secret = System.getenv("JWT_SECRET")
    )
    val hashService = SHA256HashService()

    configureSecurity(config = tokenConfig)
    configureRouting(hashService = hashService, userRepositoryImpl = userRepositoryImpl, tokenService = tokenService, tokenConfig = tokenConfig)
    configureSerialization()
    configureMonitoring()
//    configureAdministration()
//    configureSockets()
//    configureTemplating()
//    configureHTTP()
}
