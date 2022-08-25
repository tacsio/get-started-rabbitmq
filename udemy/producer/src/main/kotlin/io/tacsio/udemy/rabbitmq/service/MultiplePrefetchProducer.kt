package io.tacsio.udemy.rabbitmq.service

import io.tacsio.udemy.rabbitmq.entity.DummyMessage
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import java.time.LocalTime

@Profile("m-prefetch")
@Service
class MultiplePrefetchProducer(private var rabbitTemplate: RabbitTemplate) {

    fun simulateTransaction() {
        for(i in 1..20_000) {
            val msg = DummyMessage("Transaction ${LocalTime.now()}", i)
            rabbitTemplate.convertAndSend("x.transaction", "", msg)
        }
    }

    fun simulateScheduler() {
        for(i in 1..200) {
            val msg = DummyMessage("Scheduler ${LocalTime.now()}", i)
            rabbitTemplate.convertAndSend("x.scheduler", "", msg)
        }
    }

}