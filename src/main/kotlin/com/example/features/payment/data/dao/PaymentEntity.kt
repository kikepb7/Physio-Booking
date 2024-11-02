package com.example.features.payment.data.dao

import com.example.features.appointment.data.dao.Appointment
import com.example.features.user.data.dao.User
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.jodatime.datetime

private const val TABLE_NAME = "payment"

object Payment: IntIdTable(name = TABLE_NAME) {
    val userId = reference(name = "user_id", foreign = User).uniqueIndex().nullable()
    val appointmentId = reference(name = "appointment_id", foreign = Appointment).uniqueIndex().nullable()
    val amount = decimal(name = "amount", precision = 10, scale = 2).uniqueIndex().nullable()
    val paymentDate = datetime(name = "payment_date").uniqueIndex().nullable()
    val paymentMethod = enumeration<PaymentMethod>(name = "payment_method").uniqueIndex().nullable()
    val status = enumeration<PaymentStatus>(name = "status").uniqueIndex().nullable()
}

enum class PaymentMethod {
    CREDIT_CARD,
    CASH,
    BIZUM
}

enum class PaymentStatus {
    PENDING,
    PAID,
    FAILLED
}