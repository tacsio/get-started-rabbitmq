package io.tacsio.rabbitmq.pubsub;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class RabbitConfig {

    /*
    The fanout exchange is very simple. It just broadcasts all the messages it receives
    to all the queues it knows.
     */
    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("tacsio.fanout");
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
    public Binding binding1(FanoutExchange fanout, Queue autoDeleteQueue1) {
        return BindingBuilder.bind(autoDeleteQueue1).to(fanout);
    }

    @Bean
    public Binding binding2(FanoutExchange fanout, Queue autoDeleteQueue2) {
        return BindingBuilder.bind(autoDeleteQueue2).to(fanout);
    }


}
