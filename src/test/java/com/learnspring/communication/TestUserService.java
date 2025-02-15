package com.learnspring.communication;

import com.learnspring.communication.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestUserService {

    @Autowired
    private UserService userService;
    @Test
    public void getallusers()
    {
        List<String> users= userService.findAll();
        System.out.println(users);
    }


}
