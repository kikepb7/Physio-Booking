package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ScheduleModel(
    val id: Int? = null,
    val physiotherapistId: Int? = null,
    val dayOfWeek: String? = null,
    val startTime: String? = null,
    val endTime : String? = null,
    val available: Boolean? = null
)