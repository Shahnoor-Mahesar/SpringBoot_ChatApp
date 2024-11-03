package com.learnspring.communication.controller;

import com.learnspring.communication.config.security.JwtService;
import com.learnspring.communication.dto.LoginDto;
import com.learnspring.communication.dto.LoginResponse;
import com.learnspring.communication.dto.SignupDto;
import com.learnspring.communication.entity.User;
import com.learnspring.communication.service.AuthenticationService;
import com.learnspring.communication.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    @Autowired
    public UserController(UserService userService,JwtService jwtService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    // Display Signup form
    @GetMapping("auth/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("signupDto", new SignupDto());
        return "signup";
    }

    // Handle Signup form submission
    @PostMapping("auth/signup")
    public String signUp(@ModelAttribute SignupDto user, Model model) {

        Optional<User> entity = authenticationService.signup(user);

        if (entity.isPresent()) {
            model.addAttribute("message", "User registered successfully");
            return "redirect:/auth/login"; // Redirect to login page after signup
        } else {
            model.addAttribute("error", "Error registering user");
            return "signup"; // Stay on the signup page with an error
        }
    }

    // Display Login form
    @GetMapping("auth/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User()); // Bind an empty User object to the form
        return "login"; // Thymeleaf template name for login
    }

    @PostMapping("auth/login")
    public String login(@ModelAttribute LoginDto user, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            // Attempt to authenticate user
            User authenticatedUser = authenticationService.authenticate(user);
            if (authenticatedUser != null) {
                // Generate JWT token upon successful authentication
                String jwtToken = jwtService.generateToken(authenticatedUser);

                // Create a secure, HttpOnly cookie to store the JWT
                Cookie jwtCookie = new Cookie("jwtToken", jwtToken);
                jwtCookie.setHttpOnly(true);      // Prevents JavaScript access
                jwtCookie.setSecure(false);        // Ensures cookie is sent only over HTTPS
                jwtCookie.setPath("/");           // Makes it available to the entire application
                jwtCookie.setMaxAge(3600); // Optional: Set cookie expiration (e.g., 1 hour)

                // Add the cookie to the response
                response.addCookie(jwtCookie);
                System.out.println("User is authenticated");

                // Redirect to the home page on successful login
                return "redirect:/home";
            } else {
                // Authentication failed
                redirectAttributes.addFlashAttribute("error", "Invalid username or password.");
                return "redirect:/auth/login"; // Redirect back to login with error message
            }

        } catch (AuthenticationException e) {
            // Log error and redirect to login with an error message
            redirectAttributes.addFlashAttribute("error", "Invalid username or password. Please try again.");
            System.out.println("User is not authenticated");
            return "redirect:/auth/login"; // Redirect back to login
        }
    }



}
