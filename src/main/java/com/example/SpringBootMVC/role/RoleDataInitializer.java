package com.example.SpringBootMVC.role;

import com.example.SpringBootMVC.role.entity.Role;
import com.example.SpringBootMVC.role.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;

public class RoleDataInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;

    public RoleDataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        createRoleIfNotExists("ROLE_USER");
        createRoleIfNotExists("ROLE_ADMIN");
    }

    private void createRoleIfNotExists(String roleName) {
        roleRepository.findByName(roleName).orElseGet(() -> roleRepository.save(new Role(roleName)));
    }
}
