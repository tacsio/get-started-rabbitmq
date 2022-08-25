package io.tacsio.udemy.rabbitmq

import io.tacsio.udemy.rabbitmq.entity.InvoiceCancelledMessage
import io.tacsio.udemy.rabbitmq.entity.InvoiceCreatedMessage
import io.tacsio.udemy.rabbitmq.entity.InvoicePaidMessage
import io.tacsio.udemy.rabbitmq.service.InvoiceProducer
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

@SpringBootApplication
class ProducerApplication {

    private val log = LoggerFactory.getLogger(ProducerApplication::class.java)

    @Bean
    fun invoice(producer: InvoiceProducer) = CommandLineRunner {
        var invoiceNumber = "INV-${(100..200).random()}"
        val amount = (100..300).random().toDouble()
        val invoiceCreated = InvoiceCreatedMessage(invoiceNumber, amount, "USD", LocalDateTime.now().minusDays(2))
        producer.sendInvoiceCreated(invoiceCreated)

        invoiceNumber = "INV-${(100..200).random()}"
        val invoicePayment = "PAY-${(100..200).random()}"
        val invoicePaid = InvoicePaidMessage(invoiceNumber, invoicePayment, LocalDateTime.now())
        producer.sendInvoicePaid(invoicePaid)

        invoiceNumber = "INV-${(100..200).random()}"
        val invoiceCancelled = InvoiceCancelledMessage(invoiceNumber, "Dummy reason", LocalDateTime.now())
        producer.sendInvoiceCancelled(invoiceCancelled)

        log.info("Messages sent.")
    }

}

fun main(args: Array<String>) {
    runApplication<ProducerApplication>(*args)
}
