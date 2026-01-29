package com.example.Social_Media_RestApi_Project.role.service;

import com.example.Social_Media_RestApi_Project.role.entity.Role;
import java.util.List;


public interface RoleService {
    public List<Role> getRoles();
    public List<Role> createRoles(Role requset);
 }
