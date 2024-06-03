package com.dictionary.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
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
    private LocalDate inputDate;

    @ManyToOne
    private Language language;

    @ManyToOne
    private User addedBy;
}