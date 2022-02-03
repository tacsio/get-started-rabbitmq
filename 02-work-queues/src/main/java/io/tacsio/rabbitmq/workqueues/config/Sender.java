package io.tacsio.rabbitmq.workqueues.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Sender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    private AtomicInteger dots = new AtomicInteger(0);
    private AtomicInteger count = new AtomicInteger(0);

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
        rabbitTemplate.convertAndSend(queue.getName(), msg);

        System.out.println(" [x] Sent '" + msg + "'");
    }
}
