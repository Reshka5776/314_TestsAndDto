package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserDetailsServiceImp;

import java.security.Principal;

@Controller
public class UserController {

    private UserDetailsServiceImp userDetailsServiceImp;

    @Autowired
    public void setUserDetailsServiceImp(UserDetailsServiceImp userDetailsServiceImp) {
        this.userDetailsServiceImp = userDetailsServiceImp;
    }

    @GetMapping("/user")
    public String pageForUsers(Principal principal, Model model) {
        User loggedUser = userDetailsServiceImp.findByUsername(principal.getName());
        model.addAttribute("loggedUser", loggedUser);
        return "userForm0";
    }

}
