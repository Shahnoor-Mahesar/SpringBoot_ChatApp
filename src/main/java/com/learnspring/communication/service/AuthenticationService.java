package com.learnspring.communication.service;


import com.learnspring.communication.dto.LoginDto;
import com.learnspring.communication.dto.SignupDto;
import com.learnspring.communication.entity.User;
import com.learnspring.communication.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> signup(SignupDto input) {

      User entity = userRepository.save(User.builder()
                .username(input.getUsername())
                .password(passwordEncoder.encode(input.getPassword()))
                .email(input.getEmail())
                .fullname(input.getFirstName() + " " + input.getLastName() )
                .build());

        return Optional.of(entity);
    }

    public User authenticate(LoginDto input) {
        try {
            // Try to authenticate the user with input credentials
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            input.getEmail(),
                            input.getPassword()
                    )
            );

            // Return the user if found
            return userRepository.findByEmail(input.getEmail()).orElse(null);
        } catch (AuthenticationException e) {
            // Log the error and return null if authentication fails
            System.out.println("Authentication failed for user: " + input.getEmail());
            return null;
        }
    }

}
