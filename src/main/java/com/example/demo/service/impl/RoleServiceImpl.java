package com.example.demo.service.impl;

import com.example.demo.dao.RoleRepository;
import com.example.demo.entity.Role;
import com.example.demo.exception.RoleNotFoundException;
import com.example.demo.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRole() {
        log.info("fetching all roles");
        return roleRepository.findAll();
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getRole(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new RoleNotFoundException(name));
    }
}
