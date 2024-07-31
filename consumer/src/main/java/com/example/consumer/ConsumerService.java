package com.example.consumer;

import com.example.producer.UserMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);
    private final ConsumerRepository consumerRepository;

    public void saveMessage(UserMessage userMessage) {
        try {
            logger.info("Received UserMessage: {}", userMessage);
            UserEntity userEntity = UserEntity.builder()
                    .name(userMessage.name())
                    .email(userMessage.email())
                    .timestamp(userMessage.timestamp())
                    .build();
            consumerRepository.save(userEntity);
            logger.info("Saved UserEntity: {}", userEntity);
        } catch (Exception e) {
            logger.error("Failed to save message: {}", e.getMessage());
            throw new RuntimeException("Failed to save message", e);
        }
    }
}
