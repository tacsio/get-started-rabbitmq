package io.tacsio.udemy.rabbitmq.service

import io.tacsio.udemy.rabbitmq.entity.InvoiceCancelledMessage
import io.tacsio.udemy.rabbitmq.entity.InvoiceCreatedMessage
import io.tacsio.udemy.rabbitmq.entity.InvoicePaidMessage
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class InvoiceProducer(val rabbitTemplate: RabbitTemplate) {
    val exchange = "x.invoice"

    fun sendInvoiceCreated(message: InvoiceCreatedMessage) {
        rabbitTemplate.convertAndSend(exchange, "", message)
    }

    fun sendInvoicePaid(message: InvoicePaidMessage){
        rabbitTemplate.convertAndSend(exchange, "", message)
    }

    fun sendInvoiceCancelled(message: InvoiceCancelledMessage) {
        rabbitTemplate.convertAndSend(exchange, "", message)
    }
}