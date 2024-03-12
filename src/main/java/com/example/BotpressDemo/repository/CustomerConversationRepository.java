package com.example.BotpressDemo.repository;

import com.example.BotpressDemo.entities.CustomerConversation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerConversationRepository extends JpaRepository<CustomerConversation, Long> {
   CustomerConversation findByCustomerId(String customerId);
}