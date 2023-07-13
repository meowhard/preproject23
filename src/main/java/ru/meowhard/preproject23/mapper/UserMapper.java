package ru.meowhard.preproject23.mapper;

import org.springframework.stereotype.Component;
import ru.meowhard.preproject23.dto.UserDTO;
import ru.meowhard.preproject23.model.Request;
import ru.meowhard.preproject23.model.Role;
import ru.meowhard.preproject23.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class UserMapper {

    public User mapToUser(UserDTO userDTO) {
        return User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .vkId(userDTO.getVkId())
                .age(userDTO.getAge())
                .email(userDTO.getEmail())
                .roles(mapRolesStringToList(userDTO))
                .request(new Request(false))
                .build();
    }

    public static UserDTO mapToUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .vkId(user.getVkId())
                .age(user.getAge())
                .email(user.getEmail())
                .roles(user.getRolesToString())
                .request(user.getRequest())
                .build();
    }

    public List<Role> mapRolesStringToList(UserDTO userDTO) {
        List<Role> listOfRoles = new ArrayList<>();
        List<String> stringListOfRoles = new ArrayList<>(Arrays.asList(userDTO.getRoles().split(",")));
        for (String stringListOfRole : stringListOfRoles) {
            Role role = new Role(stringListOfRole);
            listOfRoles.add(role);
        }
        return listOfRoles;
    }
}
