package com.example.demo.service.impl;

import com.example.demo.dao.RoleRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.exception.EmailExistException;
import com.example.demo.exception.UserNotFoundException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @InjectMocks
    private UserServiceImpl userService;
    @InjectMocks
    RoleServiceImpl roleService;

    @Test
    void canSaveUser() {
        //given
       User user= new User(
                1,
                "fall",
                "abdou",
                "dabakh",
                "fall@gmail.com",
                "passer"
        );
        //when
        userService.saveUser(user);
        //then
        ArgumentCaptor<User> userArgumentCaptor= ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser).isEqualTo(user);

    }
    @Test
    void willThrowWhenEmailIsTaken() {
        //given
        User user= new User(
                1,
                "fall",
                "abdou",
                "dabakh",
                "fall@gmail.com",
                "passer"
        );
        given(userRepository.getUserByUsername(user.getUsername())).willReturn(java.util.Optional.of(user));
        //when
        //then
        assertThatThrownBy(() ->  userService.saveUser(user))
                .isInstanceOf(EmailExistException.class)
                .hasMessageContaining( String.format("User with username : %s already exist !", user.getUsername()));
        verify(userRepository,never()).save(any());
    }

    @Test
    void canGetUserByUsername() {
        User user= new User(
                1,
                "fall",
                "abdou",
                "dabakh",
                "fall@gmail.com",
                "passer"
        );
        when(userRepository.getUserByUsername(anyString())).thenReturn(java.util.Optional.of(user));
        User user1 = userService.getUser("dabakh").get();
        assertEquals("abdou",user1.getPrenom());


    }
    @Test
    void canGetUserById() {
        User user= new User(
                1,
                "fall",
                "abdou",
                "dabakh",
                "fall@gmail.com",
                "passer"
        );
        when(userRepository.findById(anyInt())).thenReturn(java.util.Optional.of(user));
        User user1 = userService.getUser(1);
        assertEquals("abdou",user1.getPrenom());


    }
    @Test
    @Disabled
    void willThrowWhenUserNotFound() {
        User user= new User(
                1,
                "fall",
                "abdou",
                "dabakh",
                "fall@gmail.com",
                "passer"
        );
        given(userRepository.getUserByUsername(anyString())).willReturn(Optional.of(user));
        assertThatThrownBy(() -> userService.getUser(user.getUsername()))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining(String.format("User with username : %s already exist !", user.getUsername()));

    }


    @Test
    void canDeleteUser() {
        //given
        User user= new User(
                1,
                "fall",
                "abdou",
                "dabakh",
                "fall@gmail.com",
                "passer"
        );
        when(userRepository.findById(user.getId())).thenReturn(java.util.Optional.of(user));
        //when
        userService.deleteUser(user.getId());
        //then
        verify(userRepository).delete(user);
    }
    @Test
    void canNotDeleteUser() {
        //given
        User user= new User(
                1,
                "fall",
                "abdou",
                "dabakh",
                "fall@gmail.com",
                "passer"
        );
        //when
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
       //then
        assertThatThrownBy(() -> userService.getUser(1))
                .isInstanceOf(UserNotFoundException.class)
                        .hasMessageContaining(String.format("User with id : %d not found",user.getId()));
    }

    @Test
    void updateUser() {
        //given
        User user= new User(
                1,
                "fall",
                "abdou",
                "dabakh",
                "fall@gmail.com",
                "uudggdzkgzcx45"
        );
        User userUpdate= new User(
                1,
                "diouf",
                "bousso",
                "bousso",
                "bousso@gmail.com",
                "qhxdadhx58xcc"
        );
        given(userRepository.findById(anyInt())).willReturn(Optional.of(user));
        User user1 = userService.getUser(1);
        user1.setUsername(userUpdate.getUsername());
        user1.setEmail(userUpdate.getEmail());
        user1.setPrenom(userUpdate.getPrenom());
        user1.setNom(userUpdate.getNom());
        user1.setPassword(userUpdate.getPassword());
        userService.updateUser(user1);
        //then
        verify(userRepository).save(user1);
    }
    @Test
    void canNotupdateUser() {
        User user= new User(
                1,
                "fall",
                "abdou",
                "dabakh",
                "fall@gmail.com",
                "uudggdzkgzcx45"
        );
        User userUpdate= new User(
                1,
                "diouf",
                "bousso",
                "bousso",
                "bousso@gmail.com",
                "qhxdadhx58xcc"
        );
        given(userRepository.findById(anyInt())).willReturn(Optional.empty());
       assertThatThrownBy(() -> userService.getUser(1))
               .isInstanceOf(UserNotFoundException.class)
               .hasMessageContaining(String.format("User with id : %d not found",user.getId()));
    }

    @Test
    void getAllUser() {
        User user= new User(
                1,
                "fall",
                "abdou",
                "dabakh",
                "fall@gmail.com",
                "uudggdzkgzcx45"
        );
        List<User> userList= new ArrayList<>();
        userList.add(user);
        //when
        when(userRepository.findAll()).thenReturn(userList);
        List<User> allUser = userService.getAllUser();
        //then
        verify(userRepository).findAll();
        assertNotNull(allUser);
        assertEquals(1,allUser.size());
    }

    @Test
    void addRoleToUser() {
        User user= new User();
        user.setId(1);
        user.setUsername("dabakh");
        Role role= new Role();
        role.setId(1);
        role.setName("ADMIN");
        given(userRepository.getUserByUsername(anyString())).willReturn(Optional.of(user));
        given(roleRepository.findByName(anyString())).willReturn(Optional.of(role));
        User user1 = userService.getUser(user.getUsername()).get();
        Role role1 = roleService.getRole(role.getName());
        user1.getRoles().add(role1);
        userService.updateUser(user1);
        verify(userRepository).save(user1);
    }

}