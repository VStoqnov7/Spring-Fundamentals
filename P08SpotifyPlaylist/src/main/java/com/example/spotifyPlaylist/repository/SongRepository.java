package com.example.spotifyPlaylist.repository;

import com.example.spotifyPlaylist.model.entity.Song;
import com.example.spotifyPlaylist.model.entity.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findByStyle(Style style);
}