package com.example.demo.dao;

import com.example.demo.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void ifStudentEmailExist() {
        //Given
        String username="dabakh";
        User user= new User(
                1,
                "fall",
                "abdou",
                username,
                "fall@gmail.com",
                "passer"
        );
        userRepository.save(user);
        //When
        User user1 = userRepository.getUserByUsername(username).get();
        //Then
        assertThat(user1).isNotNull();
    }
    @Test
    void ifStudentEmaildoesNotExist() {
        //Given
        String username="dabakh";
        User user= new User(
                1,
                "fall",
                "abdou",
                username,
                "fall@gmail.com",
                "passer"
        );
        //When
        userRepository.save(user);
        User user1 = userRepository.getUserByUsername(username).get();
        //Then
        assertThat(user1.getUsername()).isNotEqualTo("test");
    }
}