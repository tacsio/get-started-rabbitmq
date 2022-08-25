package io.tacsio.udemy.rabbitmq.service

import io.tacsio.udemy.rabbitmq.entity.DummyMessage
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Profile("default")
@Service
class DummyProducer(private var rabbitTemplate: RabbitTemplate) {

    fun sendDummy(message: DummyMessage) {
        rabbitTemplate.convertAndSend("x.dummy", "", message)
    }
}