package com.example.features.payment.domain.mapper

import com.example.features.payment.data.dao.Payment
import com.example.features.payment.domain.model.PaymentModel
import org.jetbrains.exposed.sql.ResultRow

fun paymentToDomain(row: ResultRow): PaymentModel = PaymentModel(
    id = row[Payment.id].value,
    userId = row[Payment.userId]?.value,
    appointmentId = row[Payment.appointmentId]?.value,
    amount = row[Payment.amount].toString(),
    paymentDate = row[Payment.paymentDate]?.dayOfWeek.toString(),
    paymentMethod = row[Payment.paymentMethod],
    status = row[Payment.status]
)