package ru.meowhard.preproject23.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.meowhard.preproject23.model.Request;
import ru.meowhard.preproject23.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String name);
    List<User> findAllByRequestRequestStatus(boolean requestStatus);
}