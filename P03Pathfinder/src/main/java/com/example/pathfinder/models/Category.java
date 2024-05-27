package com.example.pathfinder.models;

import com.example.pathfinder.models.enums.CategoryName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private CategoryName name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Category(CategoryName name) {
        this.name = name;
    }

    public Category() {
    }
}
