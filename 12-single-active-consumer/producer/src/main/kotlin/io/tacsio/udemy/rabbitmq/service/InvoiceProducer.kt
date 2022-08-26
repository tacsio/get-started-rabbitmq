package io.tacsio.udemy.rabbitmq.service

import io.tacsio.udemy.rabbitmq.entity.DummyMessage
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class InvoiceProducer(val rabbitTemplate: RabbitTemplate) {
    val exchange = "x.single"

    fun sendDummy(message: DummyMessage) {
        rabbitTemplate.convertAndSend(exchange, "", message)
    }
}