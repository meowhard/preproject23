package ru.meowhard.preproject23.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.meowhard.preproject23.dto.UserDTO;
import ru.meowhard.preproject23.mapper.UserMapper;
import ru.meowhard.preproject23.model.User;
import ru.meowhard.preproject23.service.UserDetailsServiceImpl;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping("/authorized")
    public UserDTO getUserData(Principal principal) {
        User user = userDetailsService.getUserByName(principal.getName());
        return UserMapper.mapToUserDTO(user);
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userDetailsService.getAllUsers().stream()
                .map(UserMapper::mapToUserDTO)
                .collect(Collectors.toList());
    }

    @PutMapping
    public void saveNewUser(@RequestBody UserDTO userDTO) {
        userDetailsService.saveUser(userDTO);
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        User user = userDetailsService.getUserById(id);
        return UserMapper.mapToUserDTO(user);
    }

    @PostMapping("/{id}")
    public void editUser(@RequestBody UserDTO userDTO, @PathVariable Long id) {
        System.out.println(userDTO.toString());
        User existingUser = userDetailsService.getUserById(id);
        userDetailsService.updateUser(userDTO, existingUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userDetailsService.deleteUserById(id);
    }
}