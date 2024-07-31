package com.example.producer;

import com.example.amqp.RabbitMQMessageProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@AllArgsConstructor
@Service
public class ProducerService {

    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public void sendMessage(UserMessage userMessage) {
        try {
            UserMessage messageWithTimestamp = new UserMessage(
                    userMessage.name(),
                    userMessage.email(),
                    new Date()
            );
            rabbitMQMessageProducer.publish(messageWithTimestamp, "internal.exchange", "internal.consumer.routing-key");
            log.info("Message sent: {}", messageWithTimestamp);
        } catch (Exception e) {
            log.error("Failed to send message: {}", e.getMessage());
            throw new RuntimeException("Failed to send message", e);
        }
    }
}
