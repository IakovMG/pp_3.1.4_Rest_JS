package com.example.pp_314.service;

import com.example.pp_314.dao.RoleDAO;
import com.example.pp_314.dao.UserDAO;
import com.example.pp_314.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserDAOServiceImp implements UserDAOService {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final PasswordEncoder passwordEncoder;

    public UserDAOServiceImp(UserDAO userDAO, RoleDAO roleDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void updateUser(Long id, User user) {
        if (user.getPassword().isEmpty()) {
            String passwordOne = userDAO.getUserById(id).getPassword();
            user.setPassword(passwordOne);
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userDAO.updateUser(id, user);
    }

    @Override
    public void deleteUser(Long id) {
        userDAO.deleteUser(id);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.saveUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }
}
