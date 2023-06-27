package ru.meowhard.preproject23.dto;

import ru.meowhard.preproject23.model.Role;
import ru.meowhard.preproject23.model.User;

import java.util.List;

public class UserDTO {
    private Long id;

    private String username;

    private String password;

    private int age;

    private String email;

    private String roles;

    public UserDTO() {
    }

    public UserDTO(Long id, String username, String password, int age, String email, List<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.email = email;
        this.roles = roles.toString();
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.age = user.getAge();
        this.email = user.getEmail();
        this.roles = user.getRolesToString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
