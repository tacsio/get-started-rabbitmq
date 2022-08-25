package io.tacsio.udemy.rabbitmq.service

import io.tacsio.udemy.rabbitmq.entity.DummyMessage
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Profile("m-prefetch")
@Service
class MultiplePrefetchConsumer {
    private val log = LoggerFactory.getLogger(MultiplePrefetchConsumer::class.java)

    @RabbitListener(queues = ["q.transaction"], concurrency = "2")
    fun transaction(message: DummyMessage) {
        log.info("Processing transaction {}", message)
        TimeUnit.MILLISECONDS.sleep(100)
    }

    @RabbitListener(queues = ["q.scheduler"], concurrency = "2",  containerFactory = "prefetchOneContainerFactory")
    fun scheduler(message: DummyMessage) {
        log.info("Processing slow {}", message)
        TimeUnit.MINUTES.sleep(1)
    }
}