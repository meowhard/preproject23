package ru.meowhard.preproject23.controller;

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

    @GetMapping("/users/authorized")
    public UserDTO getUserData(Principal principal) {
        User user = userDetailsService.getUserByName(principal.getName());
        return new UserDTO(user);
    }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        List<User> userList = userDetailsService.getAllUsers();
        return userDetailsService.getUserDTOlist(userList);
    }

    @PutMapping("/users")
    public void saveNewUser(@RequestBody UserDTO userDTO) {
        userDetailsService.saveUser(userDTO);
    }

    @GetMapping("/users/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        User user = userDetailsService.getUserById(id);
        return new UserDTO(user);
    }

    @PostMapping("/users/{id}")
    public void editUser(@RequestBody UserDTO userDTO, @PathVariable Long id) {
        System.out.println(userDTO.toString());
        User existingUser = userDetailsService.getUserById(id);
        userDetailsService.updateUser(userDTO, existingUser);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userDetailsService.deleteUserById(id);
    }
}