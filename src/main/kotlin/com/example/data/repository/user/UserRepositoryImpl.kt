package com.example.data.repository.user

import com.example.data.dto.user.UserDto
import com.example.domain.repository.user.UserRepository
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class UserRepositoryImpl(
    private val mongoDb: CoroutineDatabase
): UserRepository {

    private val users = mongoDb.getCollection<UserDto>()

    override suspend fun getUserByUsername(username: String): UserDto? {
        return users.findOne(UserDto::username eq username)
    }

    override suspend fun insertUser(user: UserDto): Boolean {
        return users.insertOne(user).wasAcknowledged()
    }
}