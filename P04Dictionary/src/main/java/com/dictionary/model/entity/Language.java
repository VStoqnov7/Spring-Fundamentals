package com.dictionary.model.entity;

import com.dictionary.model.enums.LanguageName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "languages")
public class Language extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private LanguageName languageName;


    @Column(nullable = false)
    private String description;


    @OneToMany(mappedBy = "language")
    private List<Word> words;

    public Language() {
    }

    public Language(LanguageName languageName, String description) {
        this.languageName = languageName;
        this.description = description;
    }

}
