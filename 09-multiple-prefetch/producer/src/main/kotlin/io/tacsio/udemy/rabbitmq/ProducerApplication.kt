package io.tacsio.udemy.rabbitmq

import io.tacsio.udemy.rabbitmq.service.MultiplePrefetchProducer
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class ProducerApplication {

    private val log = LoggerFactory.getLogger(ProducerApplication::class.java)

    @Bean
    fun multiplePrefetch(producer: MultiplePrefetchProducer) = CommandLineRunner {
        producer.simulateScheduler()
        producer.simulateTransaction()
        log.info("Messages sent.")
    }

}

fun main(args: Array<String>) {
    runApplication<ProducerApplication>(*args)
}
