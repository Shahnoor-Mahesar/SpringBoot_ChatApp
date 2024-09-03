package com.learnspring.communication.repository;

import com.learnspring.communication.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);
    User findByUsername(String username);
    User deleteByUsername(String username);
}
