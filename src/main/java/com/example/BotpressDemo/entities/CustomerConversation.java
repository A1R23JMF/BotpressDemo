package com.example.BotpressDemo.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Map;
@Getter
@Setter

@Entity
@Table(name = "customer_table")
public class CustomerConversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String conversationId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String channel;
    private String integration;
    @Convert(converter = MapToJsonConverter.class)
    private Map<String, Object> tags;
    private String customerId;
    private String userId;

    public void setId(Long id) {
        this.id = id;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = String.valueOf(conversationId);
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setIntegration() {
        this.integration = integration;
    }

    public void setTags(Map<String, Object> tags) {
        this.tags = tags;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}