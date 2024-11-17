package com.learnspring.communication.service;

import com.learnspring.communication.entity.ChatMessage;
import com.learnspring.communication.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {
    ChatRepository chatRepository;

    ChatService(ChatRepository chatRepository) {
        this.chatRepository=chatRepository;
    }


    public ChatMessage saveChat(ChatMessage message) {

        return chatRepository.save(message);
    }

    public List<ChatMessage> getChats(String userId) {
        return chatRepository.findBySenderIdOrRecipientId(userId, userId);
    }

    public List<ChatMessage> getGroupChats(String groupId) {
        return chatRepository.findByRecipientIdAndIsGroup(groupId, true);
    }
}

