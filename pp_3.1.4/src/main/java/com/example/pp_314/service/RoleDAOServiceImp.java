package com.example.pp_314.service;

import com.example.pp_314.dao.RoleDAO;
import com.example.pp_314.models.Role;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleDAOServiceImp implements RoleDAOService {


    private final RoleDAO roleDAO;

    public RoleDAOServiceImp(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }

    @Override
    public Role findRoleById(Long id) {
        return roleDAO.findRoleById(id);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDAO.getRoleByName(name);
    }
}
