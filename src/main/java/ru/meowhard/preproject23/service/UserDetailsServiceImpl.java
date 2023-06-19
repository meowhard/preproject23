package ru.meowhard.preproject23.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.meowhard.preproject23.model.User;
import ru.meowhard.preproject23.repository.UserRepository;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


//    public UserDetailsServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    // «Пользователь» – это просто Object. В большинстве случаев он может быть
    //  приведен к классу UserDetails.
    // Для создания UserDetails используется интерфейс UserDetailsService, с единственным методом:
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(s);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(User user) {
        User userFromDB = userRepository.findUserByUsername(user.getUsername());

        if(userFromDB != null) {
            return false;
        }

        userRepository.save(user);
        return true;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public User getUserByName(String name) {
        return userRepository.findUserByUsername(name);
    }

    public boolean updateUser(User user) {
        User userFromDB = userRepository.findUserByUsername(user.getUsername());

        if(userFromDB != null) {
            return false;
        }

        userRepository.save(user);
        return true;
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}