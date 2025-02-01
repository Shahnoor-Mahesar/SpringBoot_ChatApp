package com.learnspring.communication.service;

import com.learnspring.communication.entity.Group;
import com.learnspring.communication.repository.GroupRepository;
import org.springframework.stereotype.Service;

@Service
public class GroupService {


    private GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group createGroup(Group group) {

        return groupRepository.save(group);

    }
}
