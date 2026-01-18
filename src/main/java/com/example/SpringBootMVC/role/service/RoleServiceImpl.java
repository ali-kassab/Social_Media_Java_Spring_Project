package com.example.SpringBootMVC.role.service;

import com.example.SpringBootMVC.role.entity.Role;
import com.example.SpringBootMVC.role.repository.RoleRepository;
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
