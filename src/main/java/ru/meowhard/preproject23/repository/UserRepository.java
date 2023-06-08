package ru.meowhard.preproject23.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.meowhard.preproject23.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
