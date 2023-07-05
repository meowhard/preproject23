package ru.meowhard.preproject23.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.meowhard.preproject23.service.UserDetailsServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public AdminController(UserDetailsServiceImpl userService) {
        this.userDetailsService = userService;
    }

    @GetMapping
    public String admin(Model model, Principal principal) {
        model.addAttribute("users", userDetailsService.getAllUsers());
        model.addAttribute("user", userDetailsService.getUserByName(principal.getName()));
        return "admin";
    }
}