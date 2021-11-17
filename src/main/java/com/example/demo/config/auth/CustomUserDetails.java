package com.example.demo.config.auth;

import com.example.demo.entity.Privilege;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {
    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> collect = new ArrayList<>();
        List<SimpleGrantedAuthority> authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())).collect(Collectors.toList());
        for (Role role : user.getRoles()) {
            for (Privilege privilege : role.getPrivileges()) {
                collect.add(new SimpleGrantedAuthority(privilege.getName().getPrivilege()));
            }
        }
        authorities.addAll(collect);
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }}
