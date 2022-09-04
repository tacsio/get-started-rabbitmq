package io.tacsio.udemy.rabbitmq.service

import io.tacsio.udemy.rabbitmq.entity.DummyMessage
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.connection.CorrelationData
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class ReliableProducer(val rabbitTemplate: RabbitTemplate) {
    val log = LoggerFactory.getLogger(ReliableProducer::class.java)

    val exchange = "x.dummy2"

    @PostConstruct
    fun registerCallback() {
        rabbitTemplate.setConfirmCallback { correlationData, ack, reason ->
            run {
                log.info("correlationData {}", correlationData)
                when {
                    ack -> log.info("Message with correlation {} published", correlationData?.id)
                    else -> log.warn("Invalid exchange for message with correlation {} published", correlationData?.id)
                }
            }
        }

        rabbitTemplate.setReturnsCallback { returned ->
            run {
                log.info("Return callback")
                if (returned.replyText != null && returned.replyText.equals("NO ROUTE", ignoreCase = true)) {
                    val id =
                        returned.message.messageProperties.headers.get("spring_returned_message_correlation").toString()
                    log.warn("Invalid routing key for message {}", id)
                }
            }
        }
    }


    fun sendDummyWithInvalidRoutingKey(message: DummyMessage) {
        val correlationData = CorrelationData(message.publishOrder.toString())
        rabbitTemplate.convertAndSend(exchange, "invalid-routing-key", message)
    }


    fun sendDummyWithInvalidExchange(message: DummyMessage) {
        val correlationData = CorrelationData(message.publishOrder.toString())
        rabbitTemplate.convertAndSend("x.invalid-exchange", "", message)
    }
}