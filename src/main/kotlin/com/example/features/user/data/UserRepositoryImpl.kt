package com.example.features.user.data

import com.example.features.user.data.dao.User
import com.example.features.user.domain.UserRepository
import com.example.features.user.domain.model.UserModel
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class UserRepositoryImpl(
//    val mongoDb: CoroutineDatabase
): UserRepository {

//    private val users = mongoDb.getCollection<UserDto>()

//    override suspend fun getUserByUsernameMongoDb(username: String): UserDto? {
//        return users.findOne(UserDto::username eq username)
//    }
//
//    override suspend fun insertUserMongoDb(user: UserDto): Boolean {
//        return users.insertOne(user).wasAcknowledged()
//    }

    override suspend fun registerUser(userModel: UserModel): Int {
        return transaction {
            User.insertAndGetId {
                it[name] = userModel.name
                it[lastName] = userModel.lastName
                it[email] = userModel.email
                it[password] = userModel.password
                it[phone] = userModel.phone
                it[profileImage] = userModel.profileImage
                it[role] = userModel.role
                it[username] = userModel.username
            }.value
        }
    }

    override suspend fun findUsers(): List<UserModel>? {
        return transaction {
            User.selectAll().map {
                UserModel(
                    name = it[User.name],
                    lastName = it[User.lastName],
                    email = it[User.email],
                    phone = it[User.phone],
                    profileImage = it[User.profileImage],
                    role = it[User.role],
                    username = it[User.username]
                )
            }
        }
    }

    override suspend fun findUserById(userId: Int): UserModel? {
        return transaction {
            User.selectAll().where { User.id eq userId }
                .map {
                    UserModel(
                        name = it[User.name],
                        lastName = it[User.lastName],
                        email = it[User.email],
                        phone = it[User.phone],
                        profileImage = it[User.profileImage],
                        role = it[User.role],
                        username = it[User.username]
                    )
                }
                .singleOrNull()
        }
    }

    override suspend fun updateUserById(userId: Int, userModel: UserModel): Boolean {
        return transaction {
            val updateUsers = User.update({ User.id eq userId }) {
                it[name] = userModel.name
                it[lastName] = userModel.lastName
                it[email] = userModel.email
                it[password] = userModel.password
                it[phone] = userModel.phone
                it[profileImage] = userModel.profileImage
                it[role] = userModel.role
                it[username] = userModel.username
            }
            updateUsers > 0
        }
    }

    override suspend fun deleteUserById(userId: Int): Boolean {
        return transaction {
            val deleteUsers = User.deleteWhere { User.id eq userId }

            deleteUsers > 0
        }
    }
}