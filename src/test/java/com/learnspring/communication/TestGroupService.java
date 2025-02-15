package com.learnspring.communication;

import com.learnspring.communication.entity.Group;
import com.learnspring.communication.service.GroupService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TestGroupService {
    @Autowired
    GroupService groupService;

    @ParameterizedTest
    @ArgumentsSource(GroupArgumentsProvider.class)
    public void createGrouptest(Group group) {
        assertNotNull(groupService.createGroup(group));

    }
}
