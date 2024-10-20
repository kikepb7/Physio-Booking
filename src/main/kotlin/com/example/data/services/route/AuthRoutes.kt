package com.example.data.services.route

import com.example.security.hash.HashService
import io.ktor.server.routing.*

fun Route.signUp(
    hashService: HashService,
) {
    post("signup") {

    }
}