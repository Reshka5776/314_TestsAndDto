package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitialDataRunner implements CommandLineRunner {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) {
        Role role1 = new Role("ROLE_USER");
        Role role2 = new Role("ROLE_ADMIN");
        roleService.save(role1);
        roleService.save(role2);
        List<Role> u1roles = new ArrayList<>();
        u1roles.add(role1);
        u1roles.add(role2);

        List<Role> u2roles = new ArrayList<>();
        u2roles.add(role1);


        User user1 = new User("Admin", "Adminchik", 15, "a@a.ru", "a", u1roles);
        userService.save(user1);
        User user2 = new User("Renat", "Djan", 20, "r@r.ru", "a", u2roles);
        userService.save(user2);
        User user3 = new User("Sergey", "Sergeev", 45, "s@s.ru", "a", u2roles);
        userService.save(user3);
        User user4 = new User("Petr", "Petrov", 51, "p@p.ru", "a", u2roles);
        userService.save(user4);


    }
}

