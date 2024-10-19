package com.example.data.entity.user

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.jodatime.datetime

private const val TABLE_NAME = "user"

object User: IntIdTable(name = TABLE_NAME) {
    val name = varchar(name = "name", length = 50).uniqueIndex().nullable()
    val lastName = varchar(name = "last_name", length = 100).uniqueIndex().nullable()
    val email = varchar("email", length = 255).uniqueIndex().nullable()
    val password = varchar(name = "password", length = 255).nullable()
    val phone = varchar(name = "phone", length = 20).nullable()
    val apikey = text(name = "apikey").uniqueIndex()
    val registerDate = datetime(name = "register_time")
    val profileImage = text(name = "profile_image").nullable()
}
