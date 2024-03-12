package com.example.BotpressDemo.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageRequest {
    // Getters and setters
    private String customerId;
    private String messageText;

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
