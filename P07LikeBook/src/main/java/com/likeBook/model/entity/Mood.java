package com.likeBook.model.entity;

import com.likeBook.model.enums.MoodName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@Entity
@Table(name = "moods")
public class Mood extends BaseEntity{

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private MoodName moodName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "mood", cascade = CascadeType.ALL)
    private Set<Post> posts;

    public Mood(MoodName moodName) {
        this.moodName = moodName;
    }

    public Mood() {
    }
}
