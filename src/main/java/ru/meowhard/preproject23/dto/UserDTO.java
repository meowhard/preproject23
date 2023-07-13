package ru.meowhard.preproject23.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;

    private String vkId;

    private String username;

    private String password;

    private int age;

    private String email;

    private String roles;

    private boolean request;
}