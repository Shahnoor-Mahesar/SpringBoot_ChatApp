package com.learnspring.communication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupDto {

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
}
