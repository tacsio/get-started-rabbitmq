package io.tacsio.rabbitmq.routing;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class RabbitConfig {

    /*
     In Direct Exchange, a message goes to the queues whose binding key exactly matches
     the routing key of the message.
     */
    @Bean
    public DirectExchange direct() {
        return new DirectExchange("tacsio.direct");
    }

    @Profile("receiver")
    @Bean
    public RabbitReceiver receiver() {
        return new RabbitReceiver();
    }

    @Profile("sender")
    @Bean
    public RabbitSender sender() {
        return new RabbitSender();
    }
}

@Profile("receiver")
@Configuration
class ReceiverConfig {

    /*
    INFO
    Anonymous Queue: non-durable, exclusive, auto-delete queues in AMQP terms

    Spring AMQP client, defined an AnonymousQueue, which creates a non-durable, exclusive, auto-delete queue
    with a generated name. Once we disconnect the consumer, the queue will be automatically deleted
    */

    @Bean
    public Queue autoDeleteQueue1() {
        return new AnonymousQueue();
    }

    @Bean
    public Queue autoDeleteQueue2() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding binding1Orange(DirectExchange direct, Queue autoDeleteQueue1) {
        return BindingBuilder.bind(autoDeleteQueue1)
                .to(direct)
                .with("orange");
    }

    @Bean
    public Binding binding2Green(DirectExchange direct, Queue autoDeleteQueue2) {
        return BindingBuilder.bind(autoDeleteQueue2)
                .to(direct)
                .with("green");
    }

    @Bean
    public Binding binding2Black(DirectExchange direct, Queue autoDeleteQueue2) {
        return BindingBuilder.bind(autoDeleteQueue2)
                .to(direct)
                .with("black");
    }

}
