package com.example.pathfinder.models;

import com.example.pathfinder.models.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
}
