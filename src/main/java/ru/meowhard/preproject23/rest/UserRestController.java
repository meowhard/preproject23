package ru.meowhard.preproject23.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.meowhard.preproject23.dto.UserDTO;
import ru.meowhard.preproject23.model.User;
import ru.meowhard.preproject23.service.UserDetailsServiceImpl;

import java.security.Principal;

@RestController
public class UserRestController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping("/get_user")
    public UserDTO getUserData(Principal principal) {
        User user = userDetailsService.getUserByName(principal.getName());
        return new UserDTO(user);
    }
}