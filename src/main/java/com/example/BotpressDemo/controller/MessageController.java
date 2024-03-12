package com.example.BotpressDemo.controller;

import com.example.BotpressDemo.entities.Message;
import com.example.BotpressDemo.service.MessageService;
import com.example.BotpressDemo.dto.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
//@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/v1/chat/messages")
    public ResponseEntity<List<Message>> sendMessageAndRetrieveRecent(@RequestBody MessageRequest messageRequest) {
        try {
            List<Message> messages = messageService.sendMessageAndRetrieveRecent(messageRequest.getCustomerId(), messageRequest.getMessageText());
            return new ResponseEntity<>(messages, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/outgoing")
    public ResponseEntity<List<Message>> getOutgoingMessagesAfter(@RequestParam String conversationId, @RequestParam Long messageId) {
        List<Message> outgoingMessages = messageService.getOutgoingMessagesAfter(conversationId, messageId);
        return new ResponseEntity<>(outgoingMessages, HttpStatus.OK);
    }

    // Additional APIs (implement as needed)
}
