package com.example.SpringBootMVC.user.service;

import com.example.SpringBootMVC.user.dto.UserCreateRequest;
import com.example.SpringBootMVC.user.dto.UserResponse;
import com.example.SpringBootMVC.user.dto.UserUpdateRequest;

import java.util.List;

public interface UserService {
    String createUser(UserCreateRequest request);

    List<UserResponse> getAllUsers();
    UserResponse updateUser(Long userId, UserUpdateRequest request);
}
