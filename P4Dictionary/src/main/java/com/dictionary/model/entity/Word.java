package com.dictionary.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "words")
public class Word extends BaseEntity{

    @Column(nullable = false)
    private String term;

    @Column(nullable = false)
    private String translation;

    @Column
    private String example;

    @Column(nullable = false)
    private Date inputDate;

    @ManyToOne
    private Language language;

    @ManyToOne
    private User addedBy;
}