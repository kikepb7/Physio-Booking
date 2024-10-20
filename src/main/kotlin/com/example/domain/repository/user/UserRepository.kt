package com.example.domain.repository.user

import com.example.data.dto.user.UserDto

interface UserRepository {
    suspend fun getUserByUsername(username: String): UserDto?
    suspend fun insertUser(user: UserDto): Boolean
}