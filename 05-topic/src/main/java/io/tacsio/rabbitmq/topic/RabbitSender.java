package io.tacsio.rabbitmq.topic;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class RabbitSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange direct;

    private AtomicInteger index = new AtomicInteger(0);
    private AtomicInteger count = new AtomicInteger(0);

    private final String[] keys =  {"quick.orange.rabbit", "lazy.orange.elephant", "quick.orange.fox",
            "lazy.brown.fox", "lazy.pink.rabbit", "quick.brown.fox"};

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        var builder = new StringBuilder("Hello to ");

        if(index.incrementAndGet() == keys.length) {
            index.set(0);
        }

        var routingKey = keys[index.get()];

        builder.append(routingKey)
                .append(" [" + count.getAndIncrement() + "]");

        var msg = builder.toString();
        rabbitTemplate.convertAndSend(direct.getName(), routingKey, msg);

        System.out.println(" [x] Sent '" + msg + "'");
    }
}
