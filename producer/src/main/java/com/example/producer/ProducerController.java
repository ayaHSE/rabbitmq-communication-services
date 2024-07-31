package com.example.producer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/producer")
@RestController
@AllArgsConstructor
public class ProducerController {

 private final ProducerService producerService;

 @PostMapping("/send")
 public ResponseEntity<String> sendMessage(@RequestBody UserMessage userMessage) {
  log.info("Received request to send message: {}", userMessage);
  try {
   producerService.sendMessage(userMessage);
   return ResponseEntity.ok("Message sent to RabbitMQ!");
  } catch (Exception e) {
   log.error("Error sending message: {}", e.getMessage());
   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send message");
  }
 }

 @ExceptionHandler(Exception.class)
 public ResponseEntity<String> handleException(Exception e) {
  log.error("Exception occurred: {}", e.getMessage());
  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error occurred");
 }
}

