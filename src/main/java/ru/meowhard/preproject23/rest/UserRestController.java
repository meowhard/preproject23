package ru.meowhard.preproject23.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.meowhard.preproject23.dto.UserDTO;
import ru.meowhard.preproject23.model.User;
import ru.meowhard.preproject23.service.UserDetailsServiceImpl;

import java.security.Principal;
import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping("/get_authorized_user")
    public UserDTO getUserData(Principal principal) {
        User user = userDetailsService.getUserByName(principal.getName());
        return new UserDTO(user);
    }

    @GetMapping("/get_all_users")
    public List<UserDTO> getAllUsers() {
        List<User> userList = userDetailsService.getAllUsers();
        return userDetailsService.getUserDTOlist(userList);
    }

    @PostMapping("/saveUser")
    public void saveNewUser(@RequestBody UserDTO userDTO) {
        userDetailsService.saveUser(userDTO);
    }
}