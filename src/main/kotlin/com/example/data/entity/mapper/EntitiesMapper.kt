package com.example.data.entity.mapper

import com.example.data.entity.user.User
import com.example.domain.model.UserModel
import org.jetbrains.exposed.sql.ResultRow

fun userToDomain(row: ResultRow): UserModel = UserModel(
    id = row[User.id].value,
    name = row[User.name],
    lastName = row[User.lastName],
    email = row[User.email],
    password = row[User.password],
    phone = row[User.phone],
    apikey = row[User.apikey],
    profileImage = row[User.profileImage]
)