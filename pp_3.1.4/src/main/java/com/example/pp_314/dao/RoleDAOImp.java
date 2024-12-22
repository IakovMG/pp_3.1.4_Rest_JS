package com.example.pp_314.dao;

import com.example.pp_314.models.Role;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class RoleDAOImp implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("FROM Role", Role.class).getResultList();

    }

    @Override
    public Role findRoleById(Long id) {
        Role role = entityManager.find(Role.class, id);
        return role;
    }

    @Override
    public Role getRoleByName(String name) {
        return entityManager.createQuery("SELECT role FROM Role role WHERE role.role = :name", Role.class)
                .setParameter("name", "ROLE_" + name).getSingleResult();
    }
}
