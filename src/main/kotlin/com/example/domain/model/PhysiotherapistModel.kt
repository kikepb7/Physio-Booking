package com.example.domain.model

import com.example.data.entity.physiotherapist.Specialties
import kotlinx.serialization.Serializable

@Serializable
data class PhysiotherapistModel(
    val id: Int? = null,
    val name: String? = null,
    val lastName: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val specialties: Specialties? = null,
    val profileImage: String? = null
)