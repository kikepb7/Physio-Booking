package com.example.data.services.route

import com.example.data.repository.physiotherapist.PhysiotherapistRepositoryImpl
import com.example.data.services.response.GenericResponse
import com.example.domain.model.PhysiotherapistModel
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.exceptions.ExposedSQLException

fun Application.userRoutes(
    physiotherapistRepositoryImpl: PhysiotherapistRepositoryImpl
) {
    routing {
        // CREATE
        post("/register-physio") {
            try {
                val physioModel = call.receive<PhysiotherapistModel>()
                val physioId = physiotherapistRepositoryImpl.registerPhysio(physiotherapistModel = physioModel)

                if (physioId != null) {
                    call.respond(
                        status = HttpStatusCode.OK,
                        message = GenericResponse(
                            isSuccess = true,
                            data = "Physiotherapist registered successfully with ID: $physioId"
                        )
                    )
                } else {
                    call.respond(
                        status = HttpStatusCode.InternalServerError,
                        message = GenericResponse(isSuccess = false, data = "Failed to register the physiotherapist")
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
                    message = GenericResponse(
                        isSuccess = false,
                        data = "A physiotherapist with the same details already exists: ${e.message}"
                    )
                )
            }
        }

        // READ
        get("/physios") {
            try {
                val physios = physiotherapistRepositoryImpl.findPhysios()

                if (physios != null) {
                    if (physios.isNotEmpty()) {
                        call.respond(
                            status = HttpStatusCode.OK,
                            message = GenericResponse(isSuccess = true, data = physios)
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

        get("/physios/{physioId}") {
            val physioId = call.parameters["physioId"]?.toIntOrNull()
            if (physioId == null) {
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = GenericResponse(isSuccess = false, data = "Invalid physio ID")
                )
                return@get
            }

            try {
                val physio = physiotherapistRepositoryImpl.findPhysioById(physioId = physioId)

                if (physio != null) {
                    call.respond(HttpStatusCode.OK, physio)
                } else {
                    call.respond(
                        status = HttpStatusCode.NotFound,
                        message = GenericResponse(isSuccess = false, data = "Physiotherapist not found")
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
        put("/physio/{physioId}") {
            val physioId = call.parameters["physioId"]?.toIntOrNull()
            if (physioId == null) {
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = GenericResponse(isSuccess = false, data = "Invalid ID")
                )
                return@put
            }

            try {
                val physioModel = call.receive<PhysiotherapistModel>()
                val physioUpdated = physiotherapistRepositoryImpl.updatePhysioById(physioId = physioId, physiotherapistModel = physioModel)

                if (physioUpdated) {
                    call.respond(
                        status = HttpStatusCode.OK,
                        message = GenericResponse(isSuccess = true, data = "Physiotherapist updated successfully")
                    )
                } else {
                    call.respond(
                        status = HttpStatusCode.NotFound,
                        message = GenericResponse(isSuccess = false, data = "Physiotherapist not found")
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
        delete("/physio/{physioId}") {
            val physioId = call.parameters["physioId"]?.toIntOrNull()
            if (physioId == null) {
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = GenericResponse(isSuccess = false, data = "Invalid ID")
                )
                return@delete
            }

            try {
                val deleteRows = physiotherapistRepositoryImpl.deletePhysioById(physioId = physioId)

                if (deleteRows) {
                    call.respond(
                        status = HttpStatusCode.OK,
                        message = GenericResponse(isSuccess = true, data = "Physiotherapist deleted successfully")
                    )
                } else {
                    call.respond(
                        status = HttpStatusCode.NotFound,
                        message = GenericResponse(isSuccess = false, data = "Physiotherapist not found")
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