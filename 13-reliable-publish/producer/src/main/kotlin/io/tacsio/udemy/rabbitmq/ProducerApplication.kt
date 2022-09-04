package io.tacsio.udemy.rabbitmq

import io.tacsio.udemy.rabbitmq.entity.DummyMessage
import io.tacsio.udemy.rabbitmq.service.ReliableProducer
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class ProducerApplication {

    private val log = LoggerFactory.getLogger(ProducerApplication::class.java)

    @Bean
    fun invoice(producer: ReliableProducer) = CommandLineRunner {
        val msg1 = DummyMessage("Message 1", 1)
        val msg2 = DummyMessage("Message 2", 2)

        producer.sendDummyWithInvalidRoutingKey(msg1)
        producer.sendDummyWithInvalidRoutingKey(msg2)

        log.info("Messages sent.")
    }

}

fun main(args: Array<String>) {
    runApplication<ProducerApplication>(*args)
}
