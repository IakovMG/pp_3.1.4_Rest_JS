package com.example.pp_314.dao;

import com.example.pp_314.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImp implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void updateUser(Long id, User user) {
        user.setId(id);
        entityManager.merge(user);

    }

    @Override
    public void deleteUser(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);

    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);

    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("FROM User", User.class);
        return query.getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByEmail(String email) {
        User user = entityManager.createQuery("SELECT user from User user where user.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
        return user;
    }
}
