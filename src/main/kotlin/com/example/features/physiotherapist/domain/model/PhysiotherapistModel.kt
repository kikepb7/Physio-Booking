package com.example.features.physiotherapist.domain.model

import com.example.features.physiotherapist.data.dao.Specialties
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