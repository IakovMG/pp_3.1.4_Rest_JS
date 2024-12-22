package com.example.pp_314.service;

import com.example.pp_314.models.Role;

import java.util.List;

public interface RoleDAOService {
    List<Role> getAllRoles();

    public Role findRoleById(Long id);

    Role getRoleByName(String name);
}
