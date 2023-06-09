package ru.meowhard.preproject23.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.meowhard.preproject23.model.User;
import ru.meowhard.preproject23.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<User> getAllUsers() {
//        TODO получение списка всех юзеров
        return null;
    }
}
