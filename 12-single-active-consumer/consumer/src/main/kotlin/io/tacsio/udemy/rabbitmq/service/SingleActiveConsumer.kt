package io.tacsio.udemy.rabbitmq.service

import io.tacsio.udemy.rabbitmq.entity.DummyMessage
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class SingleActiveConsumer {
    val log = LoggerFactory.getLogger(SingleActiveConsumer::class.java)


    @RabbitListener(queues = ["q.single"], concurrency = "5")
    fun listenDummy(message: DummyMessage) {
        log.info("Consuming {}", message.toString())
        TimeUnit.SECONDS.sleep(1)
    }
}