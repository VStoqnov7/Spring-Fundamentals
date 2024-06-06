package com.likeBook.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post extends BaseEntity{

    @Column(nullable = false)
    private String content;

    @ManyToOne
    private User user;

    @ManyToMany
    private Set<User> userLikes;


    @ManyToOne
    private Mood mood;
}
