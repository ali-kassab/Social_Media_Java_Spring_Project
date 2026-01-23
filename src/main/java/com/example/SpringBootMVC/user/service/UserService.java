package com.example.SpringBootMVC.user.service;

import com.example.SpringBootMVC.user.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    String createUser(UserCreateRequest request);

    List<UserDetailsResponse> getAllUsers();

    UserResponse updateUser(Long userId, UserUpdateRequest request);

    String changePassword(ChangePasswordReq request);

    UserDetailsResponse getLoggedUserData();

    String uploadProfilePhoto(MultipartFile file);

}
