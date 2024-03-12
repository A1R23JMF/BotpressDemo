package com.example.BotpressDemo.repository;



import com.example.BotpressDemo.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import java.sql.Timestamp;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {



    List<Message> findAllByConversationIdAndOutgoingAndCreatedAtAfterOrderByCreatedAtAsc(
            String conversationId, boolean outgoing, Timestamp after);

}

