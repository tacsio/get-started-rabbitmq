package io.tacsio.rabbitmq.topic;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class RabbitConfig {

    /*
     Messages sent to a topic exchange can't have an arbitrary routing_key - it must be a list of words,
     delimited by dots. The words can be anything, but usually they specify some features connected to the message.
     A few valid routing key examples: "stock.usd.nyse", "nyse.vmw", "quick.orange.rabbit".
     There can be as many words in the routing key as you like, up to the limit of 255 bytes.

    The binding key must also be in the same form. The logic behind the topic exchange is similar to a direct one
    - a message sent with a particular routing key will be delivered to all the queues that are bound with a
    matching binding key.
     */
    @Bean
    public TopicExchange topic() {
        return new TopicExchange("tacsio.topic");
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

    /*
    In this example, we're going to send messages which all describe animals.
    The messages will be sent with a routing key that consists of three words (two dots).
    The first word in the routing key will describe speed, second a colour and third a species:

    "<speed>.<colour>.<species>".

    We created three bindings: Q1 is bound with binding key "*.orange.*" and Q2 with "*.*.rabbit" and "lazy.#".
     */
    @Bean
    public Binding binding1(TopicExchange topic, Queue autoDeleteQueue1) {
        return BindingBuilder.bind(autoDeleteQueue1)
                .to(topic)
                .with("*.orange.*");
    }

    @Bean
    public Binding binding2(TopicExchange topic, Queue autoDeleteQueue2) {
        return BindingBuilder.bind(autoDeleteQueue2)
                .to(topic)
                .with("*.*.rabbit");
    }

    @Bean
    public Binding binding3(TopicExchange topic, Queue autoDeleteQueue2) {
        return BindingBuilder.bind(autoDeleteQueue2)
                .to(topic)
                .with("lazy.#");
    }

}
