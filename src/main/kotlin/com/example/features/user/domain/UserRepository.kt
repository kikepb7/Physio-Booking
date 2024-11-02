package com.example.features.user.domain

import com.example.features.user.domain.model.UserModel

interface UserRepository {
    // MONGODB
//    suspend fun getUserByUsernameMongoDb(username: String): UserDto?
//    suspend fun insertUserMongoDb(user: UserDto): Boolean

    suspend fun registerUser(userModel: UserModel): Int
    suspend fun findUsers(): List<UserModel>?
    suspend fun findUserById(userId: Int): UserModel?
    suspend fun updateUserById(userId: Int, userModel: UserModel): Boolean
    suspend fun deleteUserById(userId: Int): Boolean
}