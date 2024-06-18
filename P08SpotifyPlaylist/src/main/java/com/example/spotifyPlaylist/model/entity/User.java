package com.example.spotifyPlaylist.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity{

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true,nullable = false)
    private String email;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_songs",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id"))
    private List<Song> playList;

    public User() {
        this.playList = new ArrayList<>();
    }
}
