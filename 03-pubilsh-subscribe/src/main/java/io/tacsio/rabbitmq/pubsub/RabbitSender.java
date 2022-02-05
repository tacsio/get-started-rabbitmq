package io.tacsio.rabbitmq.pubsub;

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
    private FanoutExchange fanout;

    private AtomicInteger count = new AtomicInteger(0);
    private AtomicInteger dots = new AtomicInteger(0);

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        var builder = new StringBuilder("Hello");

        if (dots.incrementAndGet() == 4) {
            dots.set(1);
        }

        IntStream.range(0, dots.get())
                .forEach(i -> builder.append("."));

        builder.append(count.incrementAndGet());

        var msg = builder.toString();

        var routingKey = "";
        rabbitTemplate.convertAndSend(fanout.getName(), routingKey, msg);

        System.out.println(" [x] Sent '" + msg + "'");
    }
}
