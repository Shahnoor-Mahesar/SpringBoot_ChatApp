package com.learnspring.communication.repository;

import com.learnspring.communication.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> deleteByUsername(String username);
    Optional<User> findByUsernameOrEmail(String username, String email);
}
