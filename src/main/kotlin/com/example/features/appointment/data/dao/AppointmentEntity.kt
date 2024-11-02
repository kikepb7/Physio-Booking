package com.example.features.appointment.data.dao

import com.example.features.physiotherapist.data.dao.Physiotherapist
import com.example.features.schedule.data.dao.Schedule
import com.example.features.user.data.dao.User
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.jodatime.datetime

private const val TABLE_NAME = "appointment"

object Appointment: IntIdTable(name = TABLE_NAME) {
    val userId = reference(name = "user_id", foreign = User).uniqueIndex().nullable()
    val physiotherapistId = reference(name = "physiotherapist_id", foreign = Physiotherapist).uniqueIndex().nullable()
    val scheduleId = reference(name = "schedule_id", foreign = Schedule).uniqueIndex().nullable()
    val date = datetime(name = "date").nullable()
    val status = enumerationByName<AppointmentStatus>(name = "status", length = 50).nullable()
}

enum class AppointmentStatus {
    PENDING,
    CONFIRMED,
    COMPLETED,
    CANCELED
}