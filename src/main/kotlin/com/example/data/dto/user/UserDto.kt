package com.example.data.dto.user

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class UserDto(
    @BsonId
    val id: ObjectId = ObjectId(),
    val username: String,
    val password: String,
    val salt: String
)