package io.tacsio.rabbitmq.hello;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class RabbitSender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {

        var msg = "Hello World";

        this.template.convertAndSend(queue.getName(), msg);
        System.out.println(" [x] Sent '" + msg + "'");
    }
}
