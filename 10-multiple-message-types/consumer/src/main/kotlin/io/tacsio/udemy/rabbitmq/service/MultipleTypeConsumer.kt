package io.tacsio.udemy.rabbitmq.service

import io.tacsio.udemy.rabbitmq.entity.InvoiceCreatedMessage
import io.tacsio.udemy.rabbitmq.entity.InvoicePaidMessage
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
@RabbitListener(queues = ["q.invoice"])
class MultipleTypeConsumer {
    val log = LoggerFactory.getLogger(MultipleTypeConsumer::class.java)

    @RabbitHandler
    fun handleInvoiceCreated(message: InvoiceCreatedMessage) {
        log.info("Invoice created {}", message.toString())
    }

    @RabbitHandler
    fun handleInvoicePaid(message: InvoicePaidMessage) {
        log.info("Invoice paid {}", message.toString())
    }

    @RabbitHandler(isDefault = true)
    fun handleDefault(message: Any) {
        log.info("Default handler {}", message.toString())
    }
}