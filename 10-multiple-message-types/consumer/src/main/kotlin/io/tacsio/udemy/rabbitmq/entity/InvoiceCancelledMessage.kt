package io.tacsio.udemy.rabbitmq.entity

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class InvoiceCancelledMessage(
    val invoiceNumber: String,
    val reason: String,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val cancelDate: LocalDateTime
)
