package com.example.consumer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumerRepository extends JpaRepository<UserEntity, Integer> {
}