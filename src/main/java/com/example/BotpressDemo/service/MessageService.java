package com.example.BotpressDemo.service;

import com.example.BotpressDemo.entities.CustomerConversation;
import com.example.BotpressDemo.entities.Message;
import com.example.BotpressDemo.repository.MessageRepository;
import com.example.BotpressDemo.vo.ResponseVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private CustomerConversationService customerConversationService;

    @Autowired
    private RestTemplate restTemplate; // Inject RestTemplate

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String BOT_API_URL; // Replace with your actual Botpress URL

    {
        BOT_API_URL = "https://api.botpress.cloud";
    }

    public MessageService(MessageRepository messageRepository, CustomerConversationService customerConversationService) {
        this.messageRepository = messageRepository;
        this.customerConversationService = customerConversationService;
    }

    public List<Message> sendMessageAndRetrieveRecent(String customerId, String messageText) throws IOException {
        if (customerId == null) {
            customerId = fetchValidCustomerId();
        }

        CustomerConversation conversation = customerConversationService.getOrCreateConversation(customerId);

        try {
            String url = BOT_API_URL + "/v1/chat/messages";
            String requestBody = "{\"conversationId\": \"f852c483-452f-442b-ad90-d7a654c28ffc\",\"userId\": \"3eab6638-b7e5-404e-9cc2-db60f9073c15\",\"type\": \"text\", \"channel\": \"channel\",\"tags\": {},\"direction\": \"outgoing\", \"payload\": {\"text\": \"" + messageText + "\"}, \"target\": \"" + customerId + "\"}";

            Map<String, Object> map = objectMapper.readValue(requestBody, Map.class);

            map.put("conversationId", "f852c483-452f-442b-ad90-d7a654c28ffc");
            map.put("userId", "3eab6638-b7e5-404e-9cc2-db60f9073c15");
            map.put("tags", new HashMap<>()); // Corrected value for "tags"

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("x-bot-id", "8322a3b8-0024-4793-960d-ef116a6caf4b");
            headers.add("Authorization", "Bearer bp_pat_NmTUFt3uJ3kfZoNEmMfsBpQJWTaF4DBXHhUc");
            headers.add("x-integration-id", "87b01760-ede8-49d5-afc6-6afc0d0d1bdb");
            headers.add("conversationId", "f852c483-452f-442b-ad90-d7a654c28ffc");
            headers.add("userId", "3eab6638-b7e5-404e-9cc2-db60f9073c15");

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

            ResponseEntity<ResponseVo> response = restTemplate.exchange(url, HttpMethod.POST, entity, ResponseVo.class);

            if (response.getStatusCode() != HttpStatus.OK) {
                throw new IOException("Error sending message to bot: " + response.getBody());
            }
        } catch (RestClientResponseException e) {
            throw new IOException("Error sending message to bot: " + e.getMessage());
        }

        // Save message and return outgoing messages
        Message message = new Message();
        // Set message details
        messageRepository.save(message);

        return getOutgoingMessagesAfter(String.valueOf(conversation.getConversationId()), message.getId());
    }


    private String fetchValidCustomerId() {
        // Check if a valid customerId is already available
        String customerId = null; // Placeholder, replace with actual logic to fetch from the database

        if (customerId == null) {
            // Generate a new customerId using UUID
            customerId = UUID.randomUUID().toString();
        }

        return customerId;
    }



    public List<Message> getOutgoingMessagesAfter(String conversationId, Long messageId) {
        Timestamp timestampNow = Timestamp.from(Instant.now());

        return messageRepository.findAllByConversationIdAndOutgoingAndCreatedAtAfterOrderByCreatedAtAsc(
                conversationId, true, (messageId == null) ? null : timestampNow);
    }


    // Additional APIs (implement as needed)


}



