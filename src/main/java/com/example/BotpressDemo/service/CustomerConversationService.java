package com.example.BotpressDemo.service;

import com.example.BotpressDemo.entities.CustomerConversation;
import com.example.BotpressDemo.repository.CustomerConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerConversationService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CustomerConversationRepository conversationRepository;

    private final String botpressBaseUrl = "https://api.botpress.cloud"; // Update with your Botpress base URL
    @Value("${botpress.token}")
    private String token;
    public CustomerConversation getOrCreateConversation(String customerId) {
        // Check if conversation already exists
        CustomerConversation existingConversation = conversationRepository.findByCustomerId(customerId);
        if (existingConversation != null) {
            return existingConversation;
        }

        // Create new conversation
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        headers.set("Authorization", "Bearer bp_pat_NmTUFt3uJ3kfZoNEmMfsBpQJWTaF4DBXHhUc");
        headers.set("x-bot-id", "8322a3b8-0024-4793-960d-ef116a6caf4b");
        headers.set("x-integration-id", "87b01760-ede8-49d5-afc6-6afc0d0d1bdb");

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("channel", "channel"); // Set channel
        requestBody.put("tags", new HashMap<>()); // Set tags

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> responseEntity = restTemplate.exchange(
                botpressBaseUrl + "/v1/chat/conversations/get-or-create",
                HttpMethod.POST,
                requestEntity,
                Map.class
        );

        Map<String, Object> responseBody = responseEntity.getBody();
        CustomerConversation newConversation = null;
        if (responseBody != null) {
            Map<String, Object> payload = (Map<String, Object>) responseBody.get("payload");
            if (payload != null) {
                String conversationId = (String) payload.get("id");

                // Save conversation to database
                newConversation = new CustomerConversation();
                newConversation.setConversationId(conversationId);
                newConversation.setCreatedAt(LocalDateTime.now());
                newConversation.setUpdatedAt(LocalDateTime.now());
                newConversation.setCustomerId(customerId);
                newConversation.setTags(new HashMap<>());
                newConversation.setChannel("channel"); // Set channel
                newConversation.setIntegration("87b01760-ede8-49d5-afc6-6afc0d0d1bdb"); // Set integration

                conversationRepository.save(newConversation);
            }
        }
        return newConversation;
    }
}

