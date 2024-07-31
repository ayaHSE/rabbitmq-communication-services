package com.example.producer;

import java.util.Date;

public record UserMessage(
        String name,
        String email,
        Date timestamp) {
}
