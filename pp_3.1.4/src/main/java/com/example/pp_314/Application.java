package com.example.pp_314;


import com.example.pp_314.models.Role;
import com.example.pp_314.models.User;
import com.example.pp_314.service.UserDAOServiceImp;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final UserDAOServiceImp userDAOServiceImp;

    public Application(UserDAOServiceImp userDAOServiceImp) {
        this.userDAOServiceImp = userDAOServiceImp;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<User> allUsers = userDAOServiceImp.getAllUsers();

        if (allUsers.isEmpty()) {
            Role admin = new Role("ROLE_ADMIN");
            Role user = new Role("ROLE_USER");
            HashSet<Role> roles = new HashSet<>();
            roles.add(admin);
            roles.add(user);
            userDAOServiceImp.saveUser(new User("admin", "admin", 18, "admin@admin.com", "admin", roles));
        }
    }

}
