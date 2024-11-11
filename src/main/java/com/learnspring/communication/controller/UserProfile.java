package com.learnspring.communication.controller;

import com.learnspring.communication.service.UserService;
import org.bson.types.Binary;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController("/profile")
public class UserProfile {

    private final UserService userService;
    public UserProfile(UserService userService) {
        this.userService = userService;
    }

    private String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return null;
    }

    @GetMapping("/view-picture")
    public ResponseEntity<Binary> viewProfilePicture() {

        Binary profilePicture = userService.getProfilePicture(getCurrentUsername());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(profilePicture);
    }

    // Method to upload or edit profile picture
    @PostMapping("/edit-picture")
    public ResponseEntity<String> editProfilePicture(

            @RequestParam("profilePicture") MultipartFile profilePicture) {

        boolean isUpdated = userService.updateProfilePicture(getCurrentUsername(), profilePicture);
        if (isUpdated) {
            return ResponseEntity.ok("Profile picture updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update profile picture");
        }
    }


}
