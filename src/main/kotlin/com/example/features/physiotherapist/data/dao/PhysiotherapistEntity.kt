package com.example.features.physiotherapist.data.dao

import org.jetbrains.exposed.dao.id.IntIdTable

private const val TABLE_NAME = "physiotherapist"

object Physiotherapist: IntIdTable(name = TABLE_NAME) {
    val name = varchar(name = "name", length = 50).uniqueIndex().nullable()
    val lastName = varchar(name = "last_name", length = 100).uniqueIndex().nullable()
    val email = varchar("email", length = 255).uniqueIndex().nullable()
    val phone = varchar(name = "phone", length = 20).nullable()
    val specialties = enumeration<Specialties>(name = "specialties").nullable()
    val profileImage = text(name = "profile_image").nullable()
}

enum class Specialties {
    REHABILITATION,
    MASSAGE_THERAPY,
    MANUAL_THERAPY,
    SPORTS_THERAPY,
    NEUROTHERAPY
}