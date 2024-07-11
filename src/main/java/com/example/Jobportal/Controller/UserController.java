package com.example.Jobportal.Controller;


import com.example.Jobportal.DTO.Password;
import com.example.Jobportal.Model.User;

import com.example.Jobportal.Model.UserProfileImage;
import com.example.Jobportal.Services.UserService;
import com.example.Jobportal.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class UserController {
  @Autowired
    private UserService userService;



  @PostMapping("/changePassword/{id}")
    public String changePassword(@PathVariable int id,@RequestBody Password password){
        return userService.changePassword(id,password.getPassword());
    }

    @PostMapping("/{id}/uploadProfileImage")
    public ResponseEntity<String> uploadProfileImage(@PathVariable int id, @RequestParam("file") MultipartFile file) {
        try {
            userService.saveProfileImage(id, file);
            return ResponseEntity.ok("Profile image uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload profile image");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/{id}/profileImage")
    public ResponseEntity<byte[]> getProfileImage(@PathVariable int id) {
        UserProfileImage profileImage = userService.getProfileImage(id);
        if (profileImage != null && profileImage.getProfileImage() != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(profileImage.getProfileImage());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("admin/AllUser")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> users=userService.allUser();
        return ResponseEntity.ok(users);
    }


}

