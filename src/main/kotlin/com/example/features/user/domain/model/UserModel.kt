package com.example.features.user.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    val id: Int? = null,
    val name: String? = null,
    val lastName: String? = null,
    val email: String? = null,
    val password: String? = null,
    val phone: String? = null,
    val apikey: String? = null,
    val profileImage: String? = null,
    val role: String? = null,
    val username: String? = null
)