package com.example.demo.service.impl;

import com.example.demo.dao.RoleRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.exception.EmailExistException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository,RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository=roleRepository;
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to database",user.getEmail());
        if(userRepository.getUserByUsername(user.getUsername()).isPresent()){
           throw new EmailExistException(user.getUsername());
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(String username) {
        log.info("fetching user {}",username);
        return userRepository.getUserByUsername(username);
    }

    @Override
    public User getUser(int id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("adding role {} to user {}",roleName,username);
        User user = userRepository.getUserByUsername(username).get();
        Role role = roleRepository.findByName(roleName).get();
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Override
    public Boolean deleteUser(int id) {
         userRepository.delete(getUser(id));
        return true;
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        log.info("fetching all users");
        return userRepository.findAll();
    }
}
