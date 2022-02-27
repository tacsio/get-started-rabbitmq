package io.tacsio.rabbitmq.routing;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class RabbitSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange direct;

    private AtomicInteger index = new AtomicInteger(0);
    private AtomicInteger count = new AtomicInteger(0);

    private final String[] keys =  {"orange", "green", "black"};

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        var builder = new StringBuilder("Hello to ");

        if(index.incrementAndGet() == 3) {
            index.set(0);
        }

        var routingKey = keys[index.get()];

        builder.append(routingKey)
                .append(" [" + count.getAndIncrement() + "]");

        IntStream.range(0, index.get())
                .forEach(i -> builder.append("."));

        var msg = builder.toString();
        rabbitTemplate.convertAndSend(direct.getName(), routingKey, msg);

        System.out.println(" [x] Sent '" + msg + "'");
    }
}
