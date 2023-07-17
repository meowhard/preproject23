package ru.meowhard.preproject23.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;

    private String vkId;

    private String username;

    private String password;

    private String dateOfBirth;

    private long age;

    private String email;

    private String roles;

    private boolean request;

    public LocalDate dateOfBirthAsLocalDate() {
        return LocalDate.parse(dateOfBirth);
    }
}