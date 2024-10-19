package com.example.data.entity.appointment

import com.example.data.entity.physiotherapist.Physiotherapist
import com.example.data.entity.schedule.Schedule
import com.example.data.entity.user.User
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