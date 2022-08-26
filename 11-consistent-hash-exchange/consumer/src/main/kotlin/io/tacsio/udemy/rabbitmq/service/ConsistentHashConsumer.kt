package io.tacsio.udemy.rabbitmq.service

import io.tacsio.udemy.rabbitmq.entity.InvoiceCreatedMessage
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
class ConsistentHashConsumer {
    val log = LoggerFactory.getLogger(ConsistentHashConsumer::class.java)

    @RabbitListener(queues = ["q.invoice.one"])
    fun handleQueueOne(message: InvoiceCreatedMessage) {
        log.info("Invoice queue One {}", message.toString())
    }


    @RabbitListener(queues = ["q.invoice.two"])
    fun handleQueueTwo(message: InvoiceCreatedMessage) {
        log.info("Invoice queue Two {}", message.toString())
    }
}