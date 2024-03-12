package com.example.BotpressDemo.controller;

import com.example.BotpressDemo.entities.CustomerConversation;
import com.example.BotpressDemo.service.CustomerConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.example.BotpressDemo.entities.CustomerConversation;
import com.example.BotpressDemo.service.CustomerConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conversations")
public class CustomerConversationController {

    @Autowired
    private CustomerConversationService conversationService;

    @GetMapping("/{customerId}")
    public CustomerConversation getOrCreateConversation(@PathVariable String customerId) {
        return conversationService.getOrCreateConversation(customerId);
    }


}
//    @GetMapping("/getConversation/{customerId}")
//    public ResponseEntity<CustomerConversation> getConversation(@PathVariable String customerId) {
//        CustomerConversation conversation = conversationService.getOrCreateConversation(customerId);
//        if (conversation != null) {
//            return ResponseEntity.ok(conversation);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
