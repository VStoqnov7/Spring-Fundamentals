package com.example.spotifyPlaylist.repository;

import com.example.spotifyPlaylist.model.entity.Style;
import com.example.spotifyPlaylist.model.enums.StyleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StyleRepository extends JpaRepository<Style, Long> {

    Style findByStyleName(StyleName style);
}