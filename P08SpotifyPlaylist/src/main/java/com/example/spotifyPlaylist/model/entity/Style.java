package com.example.spotifyPlaylist.model.entity;

import com.example.spotifyPlaylist.model.enums.StyleName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "styles")
public class Style extends BaseEntity{


    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private StyleName styleName;

    private String description;

    @OneToMany(mappedBy = "style")
    private List<Song> songs;
}
