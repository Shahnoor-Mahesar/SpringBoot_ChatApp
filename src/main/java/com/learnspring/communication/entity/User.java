package com.learnspring.communication.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Indexed(unique=true)
    private String username;
    private String password;
    private String email;
    private String fullname;
}
