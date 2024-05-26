package com.example.pathfinder.models;

import com.example.pathfinder.models.enums.Level;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false)
    private int age;

    @ManyToOne(cascade = CascadeType.ALL)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    private Level level;

}
