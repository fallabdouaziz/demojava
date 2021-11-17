package com.example.demo.service.impl;

import com.example.demo.dao.RoleRepository;
import com.example.demo.entity.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {
    @Mock
RoleRepository roleRepository;
    @InjectMocks
RoleServiceImpl roleService;
    @Test
    void getAllRole() {
        roleService.getAllRole();
        verify(roleRepository).findAll();
    }

    @Test
    void saveRole() {
        Role role= new Role();
        role.setId(1);
        role.setName("ADMIN");
        roleService.saveRole(role);
        verify(roleRepository).save(role);
    }

    @Test
    void canGetRole() {
        Role role= new Role();
        role.setId(1);
        role.setName("ADMIN");
        given(roleRepository.findByName(anyString())).willReturn(java.util.Optional.of(role));
        Role role1 = roleService.getRole(role.getName());
        assertEquals(role.getName(),role1.getName());
    }
}