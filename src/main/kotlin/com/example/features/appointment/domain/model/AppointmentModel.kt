package com.example.features.appointment.domain.model

import com.example.features.appointment.data.dao.AppointmentStatus
import kotlinx.serialization.Serializable

@Serializable
data class AppointmentModel(
    val id: Int? = null,
    val userId: Int? = null,
    val physiotherapistId: Int? = null,
    val scheduleId: Int? = null,
    val date: String? = null,
    val status: AppointmentStatus? = null,
)