package com.learnspring.communication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ChatListDto {
    private String name;
    private String lastMessage;
}
