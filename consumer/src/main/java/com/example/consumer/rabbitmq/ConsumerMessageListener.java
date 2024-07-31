package com.example.consumer.rabbitmq;

import com.example.consumer.ConsumerService;
import com.example.producer.UserMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class ConsumerMessageListener {

    private final ConsumerService consumerService;

    @RabbitListener(queues = "${rabbitmq.queues.consumer}")
    public void consume(UserMessage userMessage) {
        try {
            log.info("Received message: {}", userMessage);
            consumerService.saveMessage(userMessage);
        } catch (Exception e) {
            log.error("Failed to process message: {}", e.getMessage());
        }
    }
}
