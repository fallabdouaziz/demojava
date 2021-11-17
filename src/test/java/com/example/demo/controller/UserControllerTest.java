package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    UserService userService;
    @InjectMocks
    UserController userController;
    @Autowired
    MockMvc mockMvc;
    List<User> userList= new ArrayList<>();
    User user;

    @BeforeEach
    void setUp() {
        mockMvc= MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void getUsers() throws Exception {
       User user =new User();
        user.setId(1);
        user.setUsername("dabakh");
        user.setPrenom("abdou");
        User user1 =new User();
        user1.setId(2);
        user1.setUsername("bousso");
        user1.setPrenom("bousso");
        userList.add(user);
        userList.add(user1);
        when(userService.getAllUser()).thenReturn(userList);
        this.mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void create() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void getUser() {
    }
}