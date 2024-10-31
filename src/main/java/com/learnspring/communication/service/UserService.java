package com.learnspring.communication.service;

import com.learnspring.communication.entity.User;
import com.learnspring.communication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> save(User user) {
        try {
            return Optional.of(userRepository.save(user));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<User> findByusername(String username) {
        username = username.toLowerCase();

        try {
            return userRepository.findByUsername(username);
        } catch (Exception e) {
            return Optional.empty();
        }

    }

    public Optional<User> findByemail(String email) {
        email = email.toLowerCase();
        try{
            return userRepository.findByEmail(email);
        }
        catch(Exception e) {
            return Optional.empty();
        }
    }
    public Optional<User> deleteUser(User user) {

        try{
            return userRepository.deleteByUsername(user.getUsername());
        }
        catch(Exception e) {
            return Optional.empty();
        }
    }

}
