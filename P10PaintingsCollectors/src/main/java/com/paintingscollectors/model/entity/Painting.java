package com.paintingscollectors.model.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "paintings")
public class Painting extends BaseEntity{

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String author;

    @ManyToOne
    private Style style;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private boolean isFavorite;

    @Column(nullable = false)
    private int votes;

    @ManyToOne
    private User owner;

    @ManyToMany(mappedBy = "favoritePaintings", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH
    }, fetch = FetchType.EAGER)
    private Set<User> favoriteByUsers;

    @ManyToMany(mappedBy = "ratedPaintings", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH
    }, fetch = FetchType.EAGER)
    private Set<User> ratedByUsers;

    public Painting() {
        this.favoriteByUsers = new HashSet<>();
        this.ratedByUsers = new HashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Painting)) return false;
        Painting painting = (Painting) o;
        return Objects.equals(getName(), painting.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
