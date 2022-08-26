package io.tacsio.udemy.rabbitmq

import io.tacsio.udemy.rabbitmq.entity.DummyMessage
import io.tacsio.udemy.rabbitmq.service.InvoiceProducer
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.time.LocalDateTime

@SpringBootApplication
class ProducerApplication {

    private val log = LoggerFactory.getLogger(ProducerApplication::class.java)

    @Bean
    fun invoice(producer: InvoiceProducer) = CommandLineRunner {
        for (i in 1..10000) {
            val msg = DummyMessage("Message ${i}")
            producer.sendDummy(msg)
        }
        log.info("Messages sent.")
    }

}

fun main(args: Array<String>) {
    runApplication<ProducerApplication>(*args)
}
