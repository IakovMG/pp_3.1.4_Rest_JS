package com.example.pp_314.dao;


import com.example.pp_314.models.Role;

import java.util.List;


public interface RoleDAO {

    List<Role> getAllRoles();

    public Role findRoleById(Long id);

    public Role getRoleByName(String name);
}
