package com.example.features.appointment.domain.mapper

import com.example.features.appointment.data.dao.Appointment
import com.example.features.appointment.domain.model.AppointmentModel
import org.jetbrains.exposed.sql.ResultRow

fun appointmentToDomain(row: ResultRow): AppointmentModel = AppointmentModel(
    id = row[Appointment.id].value,
    userId = row[Appointment.userId]?.value,
    physiotherapistId = row[Appointment.physiotherapistId]?.value,
    scheduleId = row[Appointment.scheduleId]?.value,
    date = row[Appointment.date]?.dayOfWeek.toString(),
    status = row[Appointment.status]
)
