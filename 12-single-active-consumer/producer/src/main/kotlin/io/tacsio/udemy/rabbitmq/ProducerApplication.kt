package io.tacsio.udemy.rabbitmq

import io.tacsio.udemy.rabbitmq.entity.InvoiceCreatedMessage
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
        for (i in 1..100) {
            //create
            var invoiceNumber = "INV-${i % 30}"
            val amount = i.toDouble()
            val invoiceCreated = InvoiceCreatedMessage(invoiceNumber, amount, "USD", LocalDateTime.now().minusDays(2))

            producer.sendInvoiceCreated(invoiceCreated)
        }
        log.info("Messages sent.")
    }

}

fun main(args: Array<String>) {
    runApplication<ProducerApplication>(*args)
}
