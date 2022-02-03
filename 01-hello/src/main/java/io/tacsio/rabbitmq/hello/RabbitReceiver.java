package io.tacsio.rabbitmq.hello;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "hello")
public class RabbitReceiver {

    @RabbitHandler
    public void receive(String msg) {
        System.out.println(" [x] Received '" + msg + "'");
    }
}
