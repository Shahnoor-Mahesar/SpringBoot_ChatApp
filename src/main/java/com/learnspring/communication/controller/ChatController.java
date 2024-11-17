package com.learnspring.communication.controller;


import com.learnspring.communication.entity.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.sendMessage")
    public void sendPrivateMessage(ChatMessage message) {

        String destination = "/topic/private/" + message.getSenderId()+"/"+ message.getRecipientId();
        messagingTemplate.convertAndSend(destination, message);
    }


}
