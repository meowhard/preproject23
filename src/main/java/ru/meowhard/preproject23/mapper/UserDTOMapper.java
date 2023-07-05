package ru.meowhard.preproject23.mapper;

import org.springframework.stereotype.Component;
import ru.meowhard.preproject23.dto.UserDTO;
import ru.meowhard.preproject23.model.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class UserDTOMapper {

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