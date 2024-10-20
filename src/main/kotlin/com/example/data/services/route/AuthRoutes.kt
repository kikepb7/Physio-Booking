package com.example.data.services.route

import com.example.data.dto.user.UserDto
import com.example.data.repository.user.UserRepositoryImpl
import com.example.data.request.AuthRequest
import com.example.data.response.AuthResponse
import com.example.security.hash.HashService
import com.example.security.hash.SaltedHash
import com.example.security.token.TokenClaim
import com.example.security.token.TokenConfig
import com.example.security.token.TokenService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.apache.commons.codec.digest.DigestUtils

fun Route.signUp(
    userRepositoryImpl: UserRepositoryImpl,
    hashService: HashService
) {
    post("signup") {
        val request = kotlin.runCatching { call.receiveNullable<AuthRequest>() }.getOrNull() ?: run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }
        val areFieldsBlank = request.username.isBlank() || request.password.isBlank()
        val isPasswordTooShort = request.password.length < 8
        if (areFieldsBlank || isPasswordTooShort) {
            call.respond(HttpStatusCode.Conflict)   // Data class AuthResponse for error fields
            return@post
        }

        val saltedHash = hashService.generateSaltedHash(value = request.password)
        val user = UserDto(
            username = request.username,
            password = saltedHash.hash,
            salt = saltedHash.salt
        )
        val wasAcknowledged = userRepositoryImpl.insertUser(user = user)
        if (!wasAcknowledged) {
            call.respond(HttpStatusCode.Conflict)
            return@post
        }

        call.respond(HttpStatusCode.OK)
    }
}

fun Route.signIn(
    userRepositoryImpl: UserRepositoryImpl,
    hashService: HashService,
    tokenService: TokenService,
    tokenConfig: TokenConfig
) {
    post("signin") {
        val request = kotlin.runCatching { call.receiveNullable<AuthRequest>() }.getOrNull() ?: run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        val user = userRepositoryImpl.getUserByUsername(username = request.username)
        if (user == null) {
            call.respond(status = HttpStatusCode.Conflict, message = "Incorrect username or password")
            return@post
        }

        val isValidPassword = hashService.verify(
            value = request.password,
            saltedHash = SaltedHash(
                hash = user.password,
                salt = user.salt
            )
        )
        if (!isValidPassword) {
            println("Entered hash: ${DigestUtils.sha256Hex("${user.salt}${request.password}")}, Hashed PW: ${user.password}")
            call.respond(status = HttpStatusCode.Conflict, message = "Incorrect username or password")
            return@post
        }

        val token = tokenService.generate(
            config = tokenConfig,
            TokenClaim(
                name = "userId",
                value = user.id.toString()
            )
        )

        call.respond(status = HttpStatusCode.OK, message = AuthResponse(token = token))
    }
}

fun Route.authenticate() {
    authenticate {
        get("authenticate") {
            call.respond(HttpStatusCode.OK)
        }
    }
}

fun Route.getSecretInfo() {
    authenticate {
        get("secret-info") {
            val principal = call.principal<JWTPrincipal>()
            val userId = principal?.getClaim("userId", String::class)
            call.respond(HttpStatusCode.OK, message = "Your ID is: $userId")
        }
    }
}