package ru.meowhard.preproject23.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.meowhard.preproject23.dto.UserDTO;
import ru.meowhard.preproject23.mapper.UserMapper;
import ru.meowhard.preproject23.model.User;
import ru.meowhard.preproject23.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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

    public boolean saveUser(UserDTO userDTO) {
        User user = userMapper.mapToUser(userDTO);
        return saveUser(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public User getUserByName(String username) {
        return userRepository.findUserByUsername(username);
    }

    public void updateUser(UserDTO userDTO, User existingUser) {
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setPassword(userDTO.getPassword());
        existingUser.setAge(userDTO.getAge());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setRoles(userMapper.mapRolesStringToList(userDTO));
        userRepository.save(existingUser);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}