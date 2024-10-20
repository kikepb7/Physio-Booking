package com.example.data.entity.mapper

import com.example.data.entity.appointment.Appointment
import com.example.data.entity.payment.Payment
import com.example.data.entity.physiotherapist.Physiotherapist
import com.example.data.entity.schedule.Schedule
import com.example.data.entity.user.User
import com.example.domain.model.*
import org.jetbrains.exposed.sql.ResultRow

fun userToDomain(row: ResultRow): UserModel = UserModel(
    id = row[User.id].value,
    name = row[User.name],
    lastName = row[User.lastName],
    email = row[User.email],
    password = row[User.password],
    phone = row[User.phone],
    apikey = row[User.apikey],
    profileImage = row[User.profileImage],
    role = row[User.role],
    username = row[User.username]
)

fun physiotherapistToDomain(row: ResultRow): PhysiotherapistModel = PhysiotherapistModel(
    id = row[Physiotherapist.id].value,
    name = row[Physiotherapist.name],
    lastName = row[Physiotherapist.lastName],
    email = row[Physiotherapist.email],
    phone = row[Physiotherapist.phone],
    specialties = row[Physiotherapist.specialties],
    profileImage = row[Physiotherapist.profileImage]
)

fun scheduleToDomain(row: ResultRow): ScheduleModel = ScheduleModel(
        id = row[Schedule.id].value,
        physiotherapistId = row[Schedule.physiotherapistId]?.value,
        dayOfWeek = row[Schedule.dayOfWeek],
        startTime = row[Schedule.startTime]?.hourOfDay.toString(),
        endTime = row[Schedule.endTime]?.hourOfDay.toString(),
        available = row[Schedule.available]
)

fun appointmentToDomain(row: ResultRow): AppointmentModel = AppointmentModel(
    id = row[Appointment.id].value,
    userId = row[Appointment.userId]?.value,
    physiotherapistId = row[Appointment.physiotherapistId]?.value,
    scheduleId = row[Appointment.scheduleId]?.value,
    date = row[Appointment.date]?.dayOfWeek.toString(),
    status = row[Appointment.status]
)

fun paymentToDomain(row: ResultRow): PaymentModel = PaymentModel(
    id = row[Payment.id].value,
    userId = row[Payment.userId]?.value,
    appointmentId = row[Payment.appointmentId]?.value,
    amount = row[Payment.amount].toString(),
    paymentDate = row[Payment.paymentDate]?.dayOfWeek.toString(),
    paymentMethod = row[Payment.paymentMethod],
    status = row[Payment.status]
)