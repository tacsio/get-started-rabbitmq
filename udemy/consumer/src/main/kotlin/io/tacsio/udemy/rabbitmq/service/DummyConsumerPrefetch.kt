package io.tacsio.udemy.rabbitmq.service

import io.tacsio.udemy.rabbitmq.entity.DummyMessage
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Profile("prefetch")
@Service
class DummyConsumerPrefetch {
    private val log = LoggerFactory.getLogger(DummyConsumerPrefetch::class.java)

    @RabbitListener(queues = ["q.dummy"], concurrency = "2")
    fun listenDummy(message: DummyMessage) {
        log.info("Message is: {}", message.toString())
        TimeUnit.SECONDS.sleep(20)
    }
}