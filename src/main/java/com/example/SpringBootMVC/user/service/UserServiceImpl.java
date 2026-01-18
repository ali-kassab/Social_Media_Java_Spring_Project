package com.example.SpringBootMVC.user.service;

import com.example.SpringBootMVC.exception.ResourceAlreadyExistsException;
import com.example.SpringBootMVC.role.entity.Role;
import com.example.SpringBootMVC.role.repository.RoleRepository;
import com.example.SpringBootMVC.user.dto.UserCreateRequest;
import com.example.SpringBootMVC.user.dto.UserResponse;
import com.example.SpringBootMVC.user.dto.UserUpdateRequest;
import com.example.SpringBootMVC.user.entity.User;
import com.example.SpringBootMVC.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
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
        return "user craete successfully";
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
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
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

}
