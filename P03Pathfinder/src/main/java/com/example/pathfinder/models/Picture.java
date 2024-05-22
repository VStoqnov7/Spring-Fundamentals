package com.example.pathfinder.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity{

    @Column
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String url;

    @ManyToOne
    private User author;

    @ManyToOne
    private Route route;
}
