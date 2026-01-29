package com.example.Social_Media_RestApi_Project.user.service;

import com.example.Social_Media_RestApi_Project.user.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    String createUser(UserCreateRequest request);

    Page<UserDetailsResponse> getAllUsers(int page , int size);

    UserResponse updateUser(Long userId, UserUpdateRequest request);

    String changePassword(ChangePasswordReq request);

    UserDetailsResponse getLoggedUserData();

    String uploadProfilePhoto(MultipartFile file);
}
