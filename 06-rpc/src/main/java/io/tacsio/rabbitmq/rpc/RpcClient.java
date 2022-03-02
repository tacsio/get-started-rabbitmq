package io.tacsio.rabbitmq.rpc;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.atomic.AtomicInteger;

public class RpcClient {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange exchange;

    private AtomicInteger n = new AtomicInteger(0);

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        System.out.println(" [x] Requesting fib(" + n.get() + ")");

        var key = "rpc";
        var response = (Integer) rabbitTemplate.convertSendAndReceive(exchange.getName(), key, n.getAndIncrement());

        System.out.println(" [.] Got '" + response + "'");
    }
}
