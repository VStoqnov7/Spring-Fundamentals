package com.dictionary.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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
    private LocalDate localDate;

    @ManyToOne
    @Column(nullable = false)
    private Language language;

    @ManyToOne
    private User addedBy;
}