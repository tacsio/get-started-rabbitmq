package io.tacsio.udemy.rabbitmq.entity

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class InvoiceCreatedMessage(
    val invoiceNumber: String,
    val amount: Double,
    val currency: String,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val createdAt: LocalDateTime
)