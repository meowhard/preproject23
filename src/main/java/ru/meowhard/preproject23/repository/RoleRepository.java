package ru.meowhard.preproject23.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.meowhard.preproject23.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
