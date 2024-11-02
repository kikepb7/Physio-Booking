package com.example.features.user.domain.mapper

import com.example.features.user.data.dao.User
import com.example.features.user.domain.model.UserModel
import org.jetbrains.exposed.sql.ResultRow

fun userToDomain(row: ResultRow): UserModel = UserModel(
    id = row[User.id].value,
    name = row[User.name],
    lastName = row[User.lastName],
    email = row[User.email],
    password = row[User.password],
    phone = row[User.phone],
    apikey = row[User.apikey],
    profileImage = row[User.profileImage],
    role = row[User.role],
    username = row[User.username]
)