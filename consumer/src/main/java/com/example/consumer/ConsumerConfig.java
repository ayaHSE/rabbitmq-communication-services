package com.example.consumer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfig {
    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;

    @Value("${rabbitmq.queues.consumer}")
    private String consumerQueue;

    @Value("${rabbitmq.routing-keys.internal-consumer}")
    private String internalConsumerRoutingKey;
    //Exchange
    @Bean
    public TopicExchange internalTopicExchange() {
        return new TopicExchange(this.internalExchange);
    }
    //consumer queue
    @Bean
    public Queue consumerQueue() {
        return new Queue(this.consumerQueue);
    }
    // consumer Binding
    @Bean
    public Binding internalConsumerRoutingKey() {
        return BindingBuilder
                .bind(consumerQueue())
                .to(internalTopicExchange())
                .with(this.internalConsumerRoutingKey);
    }

    public String getInternalExchange() {
        return internalExchange;
    }

    public String getConsumerQueue() {
        return consumerQueue;
    }

    public String getInternalConsumerRoutingKey() {
        return internalConsumerRoutingKey;
    }

}
