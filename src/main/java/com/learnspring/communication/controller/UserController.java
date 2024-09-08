package com.learnspring.communication.controller;

import com.learnspring.communication.entity.User;
import com.learnspring.communication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/signup")
    public String signUp(@RequestBody User user)
    {
        Optional<User> entity= userService.save(user);
        if(entity.isPresent())
        {
            return "success user registered";
        }
        else
        {
            return "error registering user";
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody User user)
    {
        Optional<User> tempUser=userService.findByusername(user.getUsername());

        if(tempUser.isPresent())
        {
            if(user.getPassword().equals(tempUser.get().getPassword()))
            {
                return "success user logged in successfully";
            }
            else
            {
                return "error logging in";
            }
        }
        else
        {
            return "error User not found";
        }
    }

    

}
