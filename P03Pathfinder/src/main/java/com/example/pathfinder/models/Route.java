package com.example.pathfinder.models;

import com.example.pathfinder.models.enums.Level;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "routes")
public class Route extends BaseEntity{

    @Column(name = "gpx_coordinates", columnDefinition = "LONGTEXT")
    private String gpxCoordinates;

    @Column
    @Enumerated(EnumType.STRING)
    private Level level;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    private User author;

    @Column(name = "video_url")
    private String videoUrl;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Category> categories;

    @OneToMany(mappedBy = "route", fetch = FetchType.EAGER)
    private List<Picture> pictures;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<Comment> comments;
}
