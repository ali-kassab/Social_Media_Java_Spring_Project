package com.example.Social_Media_RestApi_Project.user.controller;

import com.example.Social_Media_RestApi_Project.user.dto.*;
import com.example.Social_Media_RestApi_Project.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @PostMapping
    public ResponseEntity<String> createUser(
            @Valid
            @RequestBody
            UserCreateRequest resquest
    ) {
        String response = userService.createUser(resquest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<UserDetailsResponse>> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size) {
        Page<UserDetailsResponse> users = userService.getAllUsers(page,size);
        return ResponseEntity.ok(users);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,
                                                   @RequestBody UserUpdateRequest request) {
        if (request == null) request = new UserUpdateRequest();
        UserResponse response = userService.updateUser(id, request);
        return ResponseEntity.ok(response);
    }


    @PatchMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordReq request) {
        String message = userService.changePassword(request);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/user-details")
    public ResponseEntity<UserDetailsResponse> getLoggedUserData() {
        UserDetailsResponse response = userService.getLoggedUserData();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/upload-profile-photo")
    public ResponseEntity<UploadResponse> uploadProfilePhoto(@RequestParam("file") MultipartFile file) {
        String imageUrl = userService.uploadProfilePhoto(file);
        UploadResponse response = new UploadResponse("Profile Phot uploaded successfully", imageUrl);
        return ResponseEntity.ok(response);
    }


}
