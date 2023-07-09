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
        User existingUser = userDetailsService.getUserById(id);
        userDetailsService.updateUser(userDTO, existingUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userDetailsService.deleteUserById(id);
    }

    @GetMapping("/requests")
    public List<UserDTO> getAllRequests() {
        return userDetailsService.getUsersWithRequests();
    }

    @PutMapping("/requests/{id}")
    public void saveRequest(@PathVariable Long id) {
        userDetailsService.saveRequest(id);
    }

    @DeleteMapping("/requests/{id}")
    public void denyRequest(@PathVariable Long id) {
        userDetailsService.denyRequest(id);
    }

    @GetMapping("/requests/{id}")
    public void approveRequest(@PathVariable Long id) {
        userDetailsService.approveRequest(id);
    }
}