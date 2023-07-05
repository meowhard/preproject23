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

    @GetMapping
    public String admin() {
        return "admin";
    }
}