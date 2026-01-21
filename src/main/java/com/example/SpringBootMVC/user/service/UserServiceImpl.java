package com.example.SpringBootMVC.user.service;

import com.example.SpringBootMVC.config.CloudinaryService;
import com.example.SpringBootMVC.exception.OldPasswordIncorrectException;
import com.example.SpringBootMVC.exception.ResourceAlreadyExistsException;
import com.example.SpringBootMVC.exception.UserNotFoundException;
import com.example.SpringBootMVC.role.entity.Role;
import com.example.SpringBootMVC.role.repository.RoleRepository;
import com.example.SpringBootMVC.user.dto.ChangePasswordReq;
import com.example.SpringBootMVC.user.dto.UserCreateRequest;
import com.example.SpringBootMVC.user.dto.UserResponse;
import com.example.SpringBootMVC.user.dto.UserUpdateRequest;
import com.example.SpringBootMVC.user.entity.User;
import com.example.SpringBootMVC.user.repository.UserRepository;
import com.example.SpringBootMVC.utils.Utils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private Utils utils;
    private CloudinaryService cloudinaryService;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           Utils utils,
                           CloudinaryService cloudinaryService
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.utils = utils;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public String createUser(UserCreateRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException("email already exist");
        }
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User(
                request.getName(),
                request.getEmail(),
                encodedPassword
        );
        Set<Role> roles = new HashSet<>();
        if (request.getRoles() == null || request.getRoles().isEmpty()) {
            Role userRole = roleRepository.findByName("ROLE_USER").orElseGet(() -> roleRepository.save(new Role("ROLE_USER")));
        }
        User savedUser = userRepository.save(user);
        return "user created successfully";
    }

    @Override
    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmail()
                )).collect(Collectors.toList());
    }

    @Override
    public UserResponse updateUser(Long userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
        if (request.getName() != null) {
            user.setName(request.getName());
        }
        if (request.getEmail() != null) {
            if (userRepository.existsByEmail(request.getEmail()) && !user.getEmail().equals(request.getEmail())) {
                throw new RuntimeException("email already used by some one");
            }
            user.setEmail(request.getEmail());
        }
        if (request.getName() == null && request.getEmail() == null) {
            throw new RuntimeException("must be one at least to update");
        }
        User UpdatedUser = userRepository.save(user);
        return new UserResponse(
                UpdatedUser.getId(), UpdatedUser.getName(), UpdatedUser.getEmail()
        );
    }

    @Override
    public String changePassword(ChangePasswordReq request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException());

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new OldPasswordIncorrectException();
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
        return "password changed successfully";
    }

    @Override
    public UserResponse getLoggedUserData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException());
        UserResponse response = new UserResponse(user.getId(), user.getName(), user.getEmail());
        return response;
    }

    @Override
    public String uploadProfilePhoto(MultipartFile file) {

        if (file.isEmpty()) {
            throw new RuntimeException("file is required");
        }

        Long userId = utils.getUserIDFromAuth();

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        String imageUrl = cloudinaryService.uploadImage(file);

        user.setProfileImageUrl(imageUrl);
        userRepository.save(user);

        return imageUrl;
    }


}
