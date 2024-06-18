package com.example.spotifyPlaylist.service;

import com.example.spotifyPlaylist.model.dtos.SongAddDTO;
import com.example.spotifyPlaylist.model.entity.Song;

import java.util.List;

public interface SongService {
    void saveSong(SongAddDTO songAddDTO);

    List<Song> findAllPopSongs();

    List<Song> findAllRockSongs();

    List<Song> findAllJazzSongs();

    List<Song> findAllMySongs();

    void addToMyPlaylist(Long songId);

    void removeAllMySongs();

}
