package com.example.demo.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id", nullable = false)
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id"))
    private Collection<Privilege> privileges;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Collection<Privilege> privileges) {
        this.privileges = privileges;
    }
}
