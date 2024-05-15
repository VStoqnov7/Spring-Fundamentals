package com.example.mobilele.models.entity;


import com.example.mobilele.models.enums.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "models")
public class Model extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(nullable = false,name = "image_url")
    private String imageUrl;

    @Column(nullable = false,name = "start_year")
    private int startYear;

    @Column(name = "end_year", nullable = false)
    private int endYear;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column(nullable = false)
    private LocalDateTime modified;

    @ManyToOne
    private Brand brand;
}
