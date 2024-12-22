package com.example.pp_314.controllers;


import com.example.pp_314.models.Role;
import com.example.pp_314.models.User;
import com.example.pp_314.service.RoleDAOServiceImp;
import com.example.pp_314.service.UserDAOServiceImp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("admin")
public class UserRestController {
    private final UserDAOServiceImp userDAOServiceImp;
    private final RoleDAOServiceImp roleDAOServiceImp;

    public UserRestController(UserDAOServiceImp userDAOServiceImp, RoleDAOServiceImp roleDAOServiceImp) {
        this.userDAOServiceImp = userDAOServiceImp;
        this.roleDAOServiceImp = roleDAOServiceImp;
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userDAOServiceImp.getAllUsers(), HttpStatus.OK);
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userDAOServiceImp.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/users/principal")
    public ResponseEntity<User> getPrincipal(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return new ResponseEntity<>(roleDAOServiceImp.getAllRoles(), HttpStatus.OK);
    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user, @PathVariable("id") Long id) {
        HashSet<Role> roles = new HashSet<>();
        Set<Role> selectRoles = user.getRoles();
        if (selectRoles != null && !selectRoles.isEmpty()) {
            for (Role name : selectRoles) {
                roles.add(roleDAOServiceImp.getRoleByName(name.toString()));
            }
        }
        user.setRoles(roles);
        userDAOServiceImp.updateUser(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<HttpStatus> save(@RequestBody User user) {
        HashSet<Role> roles = new HashSet<>();
        Set<Role> selectRoles = user.getRoles();
        if (selectRoles != null && !selectRoles.isEmpty()) {
            for (Role name : selectRoles) {
                roles.add(roleDAOServiceImp.getRoleByName(name.toString()));
            }
        }
        user.setRoles(roles);
        userDAOServiceImp.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        userDAOServiceImp.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
