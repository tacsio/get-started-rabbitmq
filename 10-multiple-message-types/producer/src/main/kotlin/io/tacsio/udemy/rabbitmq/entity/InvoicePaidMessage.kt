package io.tacsio.udemy.rabbitmq.entity

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class InvoicePaidMessage(
    val invoiceNumber: String,
    val paymentNumber: String,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val paidDate: LocalDateTime
)