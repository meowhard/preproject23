package ru.meowhard.preproject23.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.meowhard.preproject23.model.Role;
import ru.meowhard.preproject23.model.User;
import ru.meowhard.preproject23.service.UserDetailsServiceImpl;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("")
public class UserController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public UserController(UserDetailsServiceImpl userService) {
        this.userDetailsService = userService;
    }

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping(value = "/user")
    public String getUserPage(Model model, Principal principal) {
        model.addAttribute("user", userDetailsService.getUserByName(principal.getName()));
        return "user";
    }

    @GetMapping("/admin")
    public String listUsers(Model model) {
        model.addAttribute("users", userDetailsService.getAllUsers());
        return "admin";
    }

    @GetMapping("/admin/new")
    public String createUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "create_user";
    }

    @PostMapping("/admin")
    public String saveUser(@ModelAttribute("user") User user, Model model) {
        user.setRoles(List.of(new Role(2L, "ROLE_USER")));
        if(!userDetailsService.saveUser(user)) {
            model.addAttribute("usernameErr", "Это имя пользователя уже занято");
            return "create_user";
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String getEditUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userDetailsService.getUserById(id));
        return "edit_user";
    }

    @PostMapping("/admin/edit/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") User user) {
        User existingUser = userDetailsService.getUserById(id);
        existingUser.setName(user.getName());
        existingUser.setAge(user.getAge());
        existingUser.setEmail(user.getEmail());
        userDetailsService.updateUser(existingUser);
        return "redirect:/admin";
    }

    @GetMapping("/admin/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        userDetailsService.deleteUserById(id);
        return "redirect:/admin";
    }
}