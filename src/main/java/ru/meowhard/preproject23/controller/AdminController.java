package ru.meowhard.preproject23.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.meowhard.preproject23.model.Role;
import ru.meowhard.preproject23.model.User;
import ru.meowhard.preproject23.service.UserDetailsServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public AdminController(UserDetailsServiceImpl userService) {
        this.userDetailsService = userService;
    }

    @GetMapping
    public String showUserList(Model model) {
        model.addAttribute("users", userDetailsService.getAllUsers());
        return "admin";
    }

    @GetMapping("/new")
    public String getCreateUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "create_user";
    }

    @PostMapping
    public String saveUser(@ModelAttribute("user") User user, Model model) {
        user.setRoles(List.of(new Role(2L, "ROLE_USER")));
        if(!userDetailsService.saveUser(user)) {
            model.addAttribute("usernameErr", "Это имя пользователя уже занято");
            return "create_user";
        }
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String getEditUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userDetailsService.getUserById(id));
        return "edit_user";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") User user, Model model) {
        User existingUser = userDetailsService.getUserById(id);
        existingUser.setName(user.getName());
        existingUser.setAge(user.getAge());
        existingUser.setEmail(user.getEmail());

        if(!userDetailsService.updateUser(existingUser)) {
            model.addAttribute("usernameErr", "Это имя пользователя уже занято");
            return "edit_user";
        }
        return "redirect:/admin";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        userDetailsService.deleteUserById(id);
        return "redirect:/admin";
    }
}