package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
   private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService,PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder=passwordEncoder;
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers(){
         return userService.getAllUser();
    }

    @PreAuthorize("hasAuthority('user:create')")
    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.saveUser(user);
    }
    @PreAuthorize("hasAuthority('user:delete')")
    @GetMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean deleteUser(@PathVariable int id){
        return userService.deleteUser(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable int id){
        return userService.getUser(id);
    }
}
