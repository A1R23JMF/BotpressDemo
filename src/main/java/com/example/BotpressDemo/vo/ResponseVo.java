package com.example.BotpressDemo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class ResponseVo {
    private String id;
    private LocalDateTime createdAt;
    private String conversationId;
    private Payload payload;
    private Map<String, String> tags;
    private String userId;
    private String type;
    private String direction;

    // Getters and setters
}
