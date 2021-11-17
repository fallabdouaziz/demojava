package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    Optional<User> getUser(String username);
    User getUser(int id);
    void addRoleToUser(String username,String roleName);
    Boolean deleteUser(int id);
    User updateUser(User user);
    List<User> getAllUser();
}
