package com.example.SpringBootMVC.role.service;

import com.example.SpringBootMVC.role.entity.Role;
import java.util.List;


public interface RoleService {
    public List<Role> getRoles();
    public List<Role> createRoles(Role requset);
 }
