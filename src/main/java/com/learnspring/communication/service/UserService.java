package com.learnspring.communication.service;

import com.learnspring.communication.entity.User;
import com.learnspring.communication.repository.UserRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.bson.BsonBinary;

import java.io.IOException;
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

    public Binary getProfilePicture(String  username) {
        Optional<User> user = userRepository.findById(username);
        return user.map(User::getProfilePicture).orElse(null);
    }




    public boolean updateProfilePicture(String username, MultipartFile profilePicture) {
        try {
            Optional<User> userOptional = userRepository.findById(username);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                byte[] imageBytes = profilePicture.getBytes();
                BsonBinary bsonBinary = new BsonBinary(BsonBinarySubType.BINARY, imageBytes);

                user.setProfilePicture(new Binary(bsonBinary.getData()));
                userRepository.save(user);
                return true;
            } else {
                return false; // User not found
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Error during file conversion
        }
    }



}
