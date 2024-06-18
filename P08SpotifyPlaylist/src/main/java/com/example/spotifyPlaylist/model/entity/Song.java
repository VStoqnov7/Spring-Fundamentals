package com.example.spotifyPlaylist.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "songs")
public class Song extends BaseEntity{


    @Column(nullable = false)
    private String performer;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer duration;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @ManyToOne
    private Style style;

    @ManyToMany(mappedBy = "playList")
    private List<User> users;

    public Song() {
        this.users = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(getPerformer(), song.getPerformer()) &&
                Objects.equals(getTitle(), song.getTitle()) &&
                Objects.equals(getDuration(), song.getDuration()) &&
                Objects.equals(getReleaseDate(), song.getReleaseDate()) &&
                Objects.equals(getStyle(), song.getStyle()) &&
                Objects.equals(getUsers(), song.getUsers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getPerformer(),
                getTitle(),
                getDuration(),
                getReleaseDate(),
                getStyle(),
                getUsers()
        );
    }
}
