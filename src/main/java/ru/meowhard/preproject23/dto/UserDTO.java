package ru.meowhard.preproject23.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.meowhard.preproject23.model.Role;
import ru.meowhard.preproject23.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;

    private String username;

    private String password;

    private int age;

    private String email;

    private String roles;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.age = user.getAge();
        this.email = user.getEmail();
        this.roles = user.getRolesToString();
    }

    public List<Role> getStringRolesToList() {
        List<Role> listOfRoles = new ArrayList<>();
        List<String> stringListOfRoles = new ArrayList<>(Arrays.asList(roles.split(",")));
        for (String stringListOfRole : stringListOfRoles) {
            Role role = new Role(stringListOfRole);
            listOfRoles.add(role);
        }
        return listOfRoles;
    }
}
