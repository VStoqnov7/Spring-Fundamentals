package com.example.spotifyPlaylist.service.impl;

import com.example.spotifyPlaylist.repository.SongRepository;
import com.example.spotifyPlaylist.service.SongService;
import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }


}
