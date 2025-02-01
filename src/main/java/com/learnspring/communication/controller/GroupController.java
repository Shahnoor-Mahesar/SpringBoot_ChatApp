package com.learnspring.communication.controller;

import com.learnspring.communication.entity.Group;
import com.learnspring.communication.service.GroupService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GroupController {
    private GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/createGroup")
    public Group createGroup(Group group) {

        return groupService.createGroup(group);
    }
}
