package com.example.demo.entity;

import com.example.demo.model.PrivilegeName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "privileges")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "privilege_id", nullable = false, updatable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", unique = true)
    private PrivilegeName name;
}
