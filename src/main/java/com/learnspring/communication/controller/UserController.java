package com.learnspring.communication.controller;

import com.learnspring.communication.dto.LoginDto;
import com.learnspring.communication.dto.SignupDto;
import com.learnspring.communication.entity.User;
import com.learnspring.communication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Display Signup form
    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("signupDto", new SignupDto());
        return "signup";
    }

    // Handle Signup form submission
    @PostMapping("user/signup")
    public String signUp(@ModelAttribute SignupDto user, Model model) {

        Optional<User> entity = userService.save(User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .email(user.getEmail())
                        .fullname(user.getFirstName() + " " + user.getLastName() )
                        .build());


        if (entity.isPresent()) {
            model.addAttribute("message", "User registered successfully");
            return "redirect:/user/login"; // Redirect to login page after signup
        } else {
            model.addAttribute("error", "Error registering user");
            return "signup"; // Stay on the signup page with an error
        }
    }

    // Display Login form
    @GetMapping("user/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User()); // Bind an empty User object to the form
        return "login"; // Thymeleaf template name for login
    }

    // Handle Login form submission
    @PostMapping("user/login")
    public String login(@ModelAttribute LoginDto user, Model model) {
        Optional<User> tempUser = userService.findByemail(user.getEmail());
        if (tempUser.isPresent()) {
            if (user.getPassword().equals(tempUser.get().getPassword())) {
                model.addAttribute("message", "User logged in successfully");
                return "redirect:/home"; // Redirect to a home page after successful login
            } else {
                model.addAttribute("error", "Invalid credentials");
                return "login"; // Stay on the login page with an error
            }
        } else {
            model.addAttribute("error", "User not found");
            return "login"; // Stay on the login page with an error
        }
    }
}
