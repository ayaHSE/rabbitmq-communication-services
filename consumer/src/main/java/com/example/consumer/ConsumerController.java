package com.example.consumer;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class ConsumerController {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerController.class);
    private final ConsumerRepository consumerRepository;

    @GetMapping("/messages")
    public ResponseEntity<List<UserEntity>> getAllMessages() {
        try {
            List<UserEntity> messages = consumerRepository.findAll();
            return ResponseEntity.ok(messages);
        } catch (Exception e) {
            logger.error("Error retrieving messages: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        logger.error("Exception occurred: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error occurred");
    }
}
