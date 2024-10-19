package com.example.domain.model

import com.example.data.entity.appointment.AppointmentStatus
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