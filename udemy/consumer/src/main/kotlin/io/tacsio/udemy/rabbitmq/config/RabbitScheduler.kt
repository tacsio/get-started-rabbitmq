package io.tacsio.udemy.rabbitmq.config

import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry
import org.springframework.context.annotation.Profile
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Profile("scheduler")
@Service
@EnableScheduling
class RabbitScheduler(val registry: RabbitListenerEndpointRegistry) {

    private val log = LoggerFactory.getLogger(RabbitScheduler::class.java)

    @Scheduled(cron = "0 */3 * * * *")
    fun stopAll() {
        registry
            .listenerContainers
            .forEach {
                log.info("Stopping listener container {}", it.toString())
                it.stop()
            }
    }

    @Scheduled(cron = "0 */2 * * * *")
    fun startAll() {
        registry
            .listenerContainers
            .forEach {
                log.info("Starting listener container {}", it.toString())
                it.start()
            }
    }
}