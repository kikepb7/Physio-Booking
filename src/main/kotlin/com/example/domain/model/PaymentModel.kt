package com.example.domain.model

import com.example.data.entity.payment.PaymentMethod
import com.example.data.entity.payment.PaymentStatus
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