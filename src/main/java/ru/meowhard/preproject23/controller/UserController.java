package ru.meowhard.preproject23.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.meowhard.preproject23.service.UserDetailsServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public UserController(UserDetailsServiceImpl userService) {
        this.userDetailsService = userService;
    }

    @GetMapping
    public String getUserPage(Model model, Principal principal) {
        model.addAttribute("user", userDetailsService.getUserByName(principal.getName()));
        return "user";
    }
}