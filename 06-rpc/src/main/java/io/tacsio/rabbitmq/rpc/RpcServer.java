package io.tacsio.rabbitmq.rpc;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class RpcServer {

    @RabbitListener(queues = "tacsio.rpc.requests")
    public int fibonacci(int n) {
        System.out.println(" [x] Received request for " + n);
        int result = fib(n);
        System.out.println(" [.] Returned " + result);
        return result;
    }

    private int fib(int n) {
        return switch (n) {
            case 0 -> 0;
            case 1 -> 1;
            default -> fib(n - 1) + fib(n - 2);
        };
    }
}
