package ru.meowhard.preproject23.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.meowhard.preproject23.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
}
