package io.tacsio.rabbitmq.rpc;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class RabbitConfig {

    @Bean
    public DirectExchange rpc() {
        return new DirectExchange("tacsio.rpc");
    }

    @Profile("client")
    @Bean
    public RpcClient client() {
        return new RpcClient();
    }

    @Profile("server")
    @Bean
    public RpcServer server() {
        return new RpcServer();
    }

}

@Profile("server")
@Configuration
class ServerConfig {

    @Bean
    public Queue queue() {
        return new Queue("tacsio.rpc.requests");
    }

    @Bean
    public Binding binding(DirectExchange exchange, Queue queue) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with("rpc");
    }
}
