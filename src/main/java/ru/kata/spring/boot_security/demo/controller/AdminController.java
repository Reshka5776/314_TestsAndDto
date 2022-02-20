package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserDetailsServiceImp;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@Secured("ROLE_ADMIN")
@RestController
public class AdminController {
    private UserDetailsServiceImp userDetailsServiceImp;
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

    @Autowired
    public void setUserDetailsServiceImp(UserDetailsServiceImp userDetailsServiceImp) {
        this.userDetailsServiceImp = userDetailsServiceImp;
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/admin/loggedUser")
    public ResponseEntity<User> getLoggedUser(Principal principal) {
        User loggedUser = userDetailsServiceImp.findByUsername(principal.getName());
        return ResponseEntity.ok(loggedUser);
    }

    @GetMapping("/admin/roles")
    public ResponseEntity<List<Role>> getRoleList() {
        List<Role> listRoles = roleService.getDemandedRoles();
        return ResponseEntity.ok(listRoles);
    }

    @GetMapping("/admin/users")
    public ResponseEntity<List<User>> getUserList() {


        List<User> userList = userService.getDemandedUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("admin/users/{id}")
    public ResponseEntity<User> getSelectedUser(@PathVariable("id") Integer id, Principal principal) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/admin/users")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        User updatedUser = userService.setRolesToUser(user);
        userService.save(updatedUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("admin/users/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user,
                                        @PathVariable("id") Integer id) {
        User updatedUser = userService.setRolesToUser(user);
        userService.update(updatedUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("admin/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


