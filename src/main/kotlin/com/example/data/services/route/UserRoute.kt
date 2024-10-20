package com.example.data.services.route

import com.example.data.repository.user.UserRepositoryImpl
import com.example.data.services.response.GenericResponse
import com.example.domain.model.UserModel
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.exceptions.ExposedSQLException

fun Application.userRoutes(
    userRepositoryImpl: UserRepositoryImpl
) {
    routing {
        get("/") {
            call.respondText("Welcome to Physio-Booking App")
        }

        // CREATE
        post("/register-user") {
            try {
                val userModel = call.receive<UserModel>()
                val userId = userRepositoryImpl.registerUser(userModel = userModel)

                if (userId != null) {
                    call.respond(
                        status = HttpStatusCode.OK,
                        message = GenericResponse(isSuccess = true, data = "User registered successfully with ID: $userId")
                    )
                } else {
                    call.respond(
                        status = HttpStatusCode.InternalServerError,
                        message = GenericResponse(isSuccess = false, data = "Failed to register the user")
                    )
                }
            } catch (e: Exception) {
                call.respond(
                    status = HttpStatusCode.InternalServerError,
                    message = GenericResponse(isSuccess = false, data = "Internal server error: ${e.message}")
                )
            } catch (e: ExposedSQLException) {
                call.respond(
                    status = HttpStatusCode.Conflict,
                    message = GenericResponse(isSuccess = false, data = "A user with the same details already exists: ${e.message}")
                )
            }
        }

        // READ
        get("/users") {
            try {
                val users = userRepositoryImpl.findUsers()

                if (users != null) {
                    if (users.isNotEmpty()) {
                        call.respond(
                            status = HttpStatusCode.OK,
                            message = GenericResponse(isSuccess = true, data = users)
                        )
                    } else {
                        call.respond(
                            status = HttpStatusCode.OK,
                            message = GenericResponse(isSuccess = false, data = null)
                        )
                    }
                }

            } catch (e: Exception) {
                call.respond(
                    status = HttpStatusCode.InternalServerError,
                    message = GenericResponse(isSuccess = false, data = "Internal server error: ${e.message}")
                )
            }
        }

        get("/user/{userId}") {
            val userId = call.parameters["userId"]?.toIntOrNull()
            if (userId == null) {
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = GenericResponse(isSuccess = false, data = "Invalid user ID")
                )
                return@get
            }

            try {
                val user = userRepositoryImpl.findUserById(userId = userId)

                if (user != null) {
                    call.respond(HttpStatusCode.OK, user)
                } else {
                    call.respond(
                        status = HttpStatusCode.NotFound,
                        message = GenericResponse(isSuccess = false, data = "User not found")
                    )
                }
            } catch (e: Exception) {
                call.respond(
                    status = HttpStatusCode.InternalServerError,
                    message = GenericResponse(isSuccess = false, data = "Internal server error: ${e.message}")
                )
            }
        }

        // UPDATE
        put("/user/{userId}") {
            val id = call.parameters["userId"]?.toIntOrNull()
            if (id == null) {
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = GenericResponse(isSuccess = false, data = "Invalid ID")
                )
                return@put
            }

            try {
                val userModel = call.receive<UserModel>()
                val userUpdated = userRepositoryImpl.updateUserById(userId = id, userModel = userModel)

                if (userUpdated) {
                    call.respond(
                        status = HttpStatusCode.OK,
                        message = GenericResponse(isSuccess = true, data = "User updated successfully")
                    )
                } else {
                    call.respond(
                        status = HttpStatusCode.NotFound,
                        message = GenericResponse(isSuccess = false, data = "User not found")
                    )
                }
            } catch (e: Exception) {
                call.respond(
                    status = HttpStatusCode.InternalServerError,
                    message = GenericResponse(isSuccess = false, data = "Internal server error: ${e.message}")
                )
            }
        }

        // DELETE
        delete("/user/{userId}") {
            val id = call.parameters["userId"]?.toIntOrNull()
            if (id == null) {
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = GenericResponse(isSuccess = false, data = "Invalid ID")
                )
                return@delete
            }

            try {
                val deleteRows = userRepositoryImpl.deleteUserById(userId = id)

                if (deleteRows) {
                    call.respond(
                        status = HttpStatusCode.OK,
                        message = GenericResponse(isSuccess = true, data = "User deleted successfully")
                    )
                } else {
                    call.respond(
                        status = HttpStatusCode.NotFound,
                        message = GenericResponse(isSuccess = false, data = "User not found")
                    )
                }
            } catch (e: Exception) {
                call.respond(
                    status = HttpStatusCode.InternalServerError,
                    message = GenericResponse(isSuccess = false, data = "Internal server error: ${e.message}")
                )
            }
        }
    }
}