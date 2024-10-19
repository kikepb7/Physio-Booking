package com.example.application.plugins

import com.example.data.datasource.local.DbConnection
import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.resources.Resources
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

fun Application.configureRouting() {

    val db = DbConnection.getDatabaseInstance()

}
