package com.example.features.payment.domain.model

import com.example.features.payment.data.dao.PaymentMethod
import com.example.features.payment.data.dao.PaymentStatus
import kotlinx.serialization.Serializable

@Serializable
data class PaymentModel(
    val id: Int? = null,
    val userId: Int? = null,
    val appointmentId: Int? = null,
    val amount: String? = null,
    val paymentDate: String? = null,
    val paymentMethod: PaymentMethod? = null,
    val status: PaymentStatus? = null
)