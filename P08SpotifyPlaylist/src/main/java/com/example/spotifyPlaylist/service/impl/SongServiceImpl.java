package com.example.spotifyPlaylist.service.impl;

import com.example.spotifyPlaylist.model.dtos.SongAddDTO;
import com.example.spotifyPlaylist.model.entity.Song;
import com.example.spotifyPlaylist.repository.SongRepository;
import com.example.spotifyPlaylist.service.SongService;
import com.example.spotifyPlaylist.service.StyleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final ModelMapper modelMapper;
    private final StyleService styleService;

    public SongServiceImpl(SongRepository songRepository, ModelMapper modelMapper, StyleService styleService) {
        this.songRepository = songRepository;
        this.modelMapper = modelMapper;
        this.styleService = styleService;
    }


    @Override
    public void saveSong(SongAddDTO songAddDTO) {
        Song song = modelMapper.map(songAddDTO,Song.class);
        song.setStyle(this.styleService.findStyleByName(songAddDTO.getStyle()));
        this.songRepository.saveAndFlush(song);
    }
}
