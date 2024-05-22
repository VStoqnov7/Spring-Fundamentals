package com.example.pathfinder.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment extends BaseEntity{

    @Column
    private Boolean approved;

    @Column
    private LocalDateTime created;

    @Column(name = "text_content",columnDefinition = "TEXT")
    private String textContent;

    @ManyToOne
    private User author;

    @ManyToOne
    private Route route;
}
