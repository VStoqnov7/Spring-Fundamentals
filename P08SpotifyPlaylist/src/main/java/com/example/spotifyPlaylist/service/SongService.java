package com.example.spotifyPlaylist.service;

import com.example.spotifyPlaylist.model.dtos.SongAddDTO;

public interface SongService {
    void saveSong(SongAddDTO songAddDTO);
}
