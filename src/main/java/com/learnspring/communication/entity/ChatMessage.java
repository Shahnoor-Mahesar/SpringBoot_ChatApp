package com.learnspring.communication.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "chats")
public class ChatMessage {

    private String senderId;
    private String recipientId; // User ID or Group ID
    private String message;
    private LocalDateTime timestamp;
    private boolean isGroup;

    // Getters and setters
}
