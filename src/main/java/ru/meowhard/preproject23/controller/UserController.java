package ru.meowhard.preproject23.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.meowhard.preproject23.model.User;
import ru.meowhard.preproject23.service.UserDetailsServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public String user(Model model, Principal principal) {
        return "user";
    }
}