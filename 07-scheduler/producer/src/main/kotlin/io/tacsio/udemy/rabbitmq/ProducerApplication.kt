package io.tacsio.udemy.rabbitmq

import io.tacsio.udemy.rabbitmq.entity.DummyMessage
import io.tacsio.udemy.rabbitmq.service.DummyProducer
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
import java.time.LocalTime.now
import java.util.concurrent.TimeUnit

@SpringBootApplication
class ProducerApplication {

    private val log = LoggerFactory.getLogger(ProducerApplication::class.java)

    @Bean
    fun scheduler(producer: DummyProducer) = CommandLineRunner {
        for(i in 1..10_000){
            val msg = DummyMessage("New is ${now()}", i)
            producer.sendDummy(msg)
            TimeUnit.SECONDS.sleep(1)
        }
        log.info("Messages sent.")
    }
}

fun main(args: Array<String>) {
    runApplication<ProducerApplication>(*args)
}
