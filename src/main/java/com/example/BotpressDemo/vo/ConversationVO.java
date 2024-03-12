package com.example.BotpressDemo.vo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Data

@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)


public class ConversationVO {
    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String channel;
    private String integration;
    private Map<String, Object> tags;
    @Getter
    private char[] customerId;
    @Getter
    private String userId;

    // Getters and Setters

    public void setId(String id) {
        this.id = id;
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

    public void setIntegration(String integration) {
        this.integration = integration;
    }

    public void setTags(Map<String, Object> tags) {
        this.tags = tags;
    }


    public void setCustomerId(char[] customerId) {
        this.customerId = customerId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}