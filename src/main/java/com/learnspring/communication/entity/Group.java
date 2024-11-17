package com.learnspring.communication.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "groups")
public class Group {
    @Id
    private String id;
    private String groupName;
    private List<String> members; // List of user IDs
    private LocalDateTime createdAt;
    private Binary profilePicture;
    // Getters and setters
}
