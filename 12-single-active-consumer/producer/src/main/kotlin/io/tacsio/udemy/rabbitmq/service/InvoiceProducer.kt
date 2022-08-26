package io.tacsio.udemy.rabbitmq.service

import io.tacsio.udemy.rabbitmq.entity.InvoiceCreatedMessage
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class InvoiceProducer(val rabbitTemplate: RabbitTemplate) {
    val exchange = "x.invoice"

    fun sendInvoiceCreated(message: InvoiceCreatedMessage) {
        rabbitTemplate.convertAndSend(exchange, message.invoiceNumber, message)
    }
}