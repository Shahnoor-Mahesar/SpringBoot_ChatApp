package com.learnspring.communication.repository;

import com.learnspring.communication.entity.Group;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupRepository extends MongoRepository<Group, String> {

    //TODO:implement_extra_methods
}

