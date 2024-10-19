package com.example.data.datasource.local

import org.jetbrains.exposed.sql.Database


object DbConnection {
    private val db: Database ?= null
    private val hostname = System.getenv("DB_HOST")?: "127.0.0.1"
    private val databaseName = System.getenv("DB_NAME")?: "physio_booking"
    private val driver = "com.mysql.cj.jdbc.Driver"
    private val user = System.getenv("DB_USER")?: "root"
    private val password = System.getenv("DB_PASSWORD")?: ""
    private val jdbcUrl = "jdbc:mysql://$hostname:3306/$databaseName"

    fun getDatabaseInstance(): Database {
        return db ?: Database.connect(
            url = jdbcUrl,
            driver = driver,
            user = user,
            password = password
        )
    }
}