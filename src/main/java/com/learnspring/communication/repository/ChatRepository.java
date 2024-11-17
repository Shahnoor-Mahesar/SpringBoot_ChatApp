package com.learnspring.communication.repository;

import com.learnspring.communication.entity.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatRepository extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findBySenderIdOrRecipientId(String senderId, String recipientId);
    List<ChatMessage> findByRecipientIdAndIsGroup(String recipientId, boolean isGroup);
}
