package com.example.Social_Media_RestApi_Project.role.service;

import com.example.Social_Media_RestApi_Project.role.entity.Role;
import com.example.Social_Media_RestApi_Project.role.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    public List<Role> createRoles(Role request) {
        Optional<Role> existRole = roleRepository.findByName(request.getName());
        if (!existRole.isPresent()) {
            throw new RuntimeException("Role already Exist");
        }
        roleRepository.save(request);
        return new ArrayList<Role>();
    }
}
