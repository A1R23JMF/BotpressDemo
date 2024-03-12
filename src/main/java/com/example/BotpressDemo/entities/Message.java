package com.example.BotpressDemo.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;


@Data

@Entity
@Table(name = "message_table")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String responseId;
    private LocalDateTime createdAt;
    private String conversationId;
    private String payload;
    @Convert(converter = MapToJsonConverter.class)
    private Map<String, String> tags;
    private String userId;
    private String type;
    private String direction;
    // Getter and Setter for 'outgoing'
    @Getter
    private boolean outgoing; // Add the 'outgoing' property

    public void setOutgoing(boolean outgoing) {
        this.outgoing = outgoing;
    }

    // Getters and setters
}