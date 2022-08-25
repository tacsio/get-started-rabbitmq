package io.tacsio.udemy.rabbitmq.service

import io.tacsio.udemy.rabbitmq.entity.DummyMessage
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
class DummyConsumer {
    private val log = LoggerFactory.getLogger(DummyConsumer::class.java)

    @RabbitListener(queues = ["q.dummy"])
    fun listenDummy(message: DummyMessage) {
        log.info("Message is: {}", message.toString())
    }
}