package com.example.Social_Media_RestApi_Project.user.service;

import com.example.Social_Media_RestApi_Project.config.CloudinaryService;
import com.example.Social_Media_RestApi_Project.exception.OldPasswordIncorrectException;
import com.example.Social_Media_RestApi_Project.exception.ResourceAlreadyExistsException;
import com.example.Social_Media_RestApi_Project.exception.UserNotFoundException;
import com.example.Social_Media_RestApi_Project.role.entity.Role;
import com.example.Social_Media_RestApi_Project.role.repository.RoleRepository;
import com.example.Social_Media_RestApi_Project.user.dto.*;
import com.example.Social_Media_RestApi_Project.user.entity.User;
import com.example.Social_Media_RestApi_Project.user.repository.UserRepository;
import com.example.Social_Media_RestApi_Project.utils.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashSet;
import java.util.Set;

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
    public Page<UserDetailsResponse> getAllUsers(int page ,int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").ascending());
        Page<User> users = userRepository.findAll(pageable);

        return users.map(user -> new UserDetailsResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getProfileImage(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        ));
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
                UpdatedUser.getId(), UpdatedUser.getName(), UpdatedUser.getEmail(), UpdatedUser.getProfileImage()
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
    public UserDetailsResponse getLoggedUserData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException());
        String profileImageUrl = user.getProfileImage() != null
                ? cloudinaryService.buildImageUrl(user.getProfileImage())
                : null;
        UserDetailsResponse response = new UserDetailsResponse(user.getId(), user.getName(), user.getEmail(), profileImageUrl, user.getCreatedAt(), user.getUpdatedAt());

        return response;
    }

    @Override
    public String uploadProfilePhoto(MultipartFile file) {

        if (file.isEmpty()) {
            throw new RuntimeException("file is required");
        }

        Long userId = utils.getUserAuthData().getId();

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        String publicId = cloudinaryService.uploadImage(file);
        user.setProfileImageUrl(publicId);
        userRepository.save(user);

        return cloudinaryService.buildImageUrl(publicId);
    }


}
