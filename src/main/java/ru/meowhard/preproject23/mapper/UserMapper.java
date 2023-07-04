package ru.meowhard.preproject23.mapper;

import org.springframework.stereotype.Component;
import ru.meowhard.preproject23.dto.UserDTO;
import ru.meowhard.preproject23.model.User;

@Component
public class UserMapper {
    public User mapToUser(UserDTO userDTO) {
        return User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .age(userDTO.getAge())
                .email(userDTO.getEmail())
                .roles(userDTO.getStringRolesToList())
                .build();
    }
}
