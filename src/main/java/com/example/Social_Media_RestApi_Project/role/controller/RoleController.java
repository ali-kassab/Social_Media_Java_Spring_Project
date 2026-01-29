package com.example.Social_Media_RestApi_Project.role.controller;

import com.example.Social_Media_RestApi_Project.role.entity.Role;
import com.example.Social_Media_RestApi_Project.role.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getRoles();
        return ResponseEntity.ok(roles);
    }

    @PostMapping
    public ResponseEntity<List<Role>> createRoles(@RequestBody Role request) {
        List<Role> roles = roleService.createRoles(request);
        return ResponseEntity.ok(roles);
    }
}
