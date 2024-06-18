package com.example.spotifyPlaylist.service.impl;

import com.example.spotifyPlaylist.model.dtos.SongAddDTO;
import com.example.spotifyPlaylist.model.entity.Song;
import com.example.spotifyPlaylist.model.entity.User;
import com.example.spotifyPlaylist.model.enums.StyleName;
import com.example.spotifyPlaylist.repository.SongRepository;
import com.example.spotifyPlaylist.service.SongService;
import com.example.spotifyPlaylist.service.StyleService;
import com.example.spotifyPlaylist.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final ModelMapper modelMapper;
    private final StyleService styleService;
    private final UserService userService;

    public SongServiceImpl(SongRepository songRepository, ModelMapper modelMapper, StyleService styleService, UserService userService) {
        this.songRepository = songRepository;
        this.modelMapper = modelMapper;
        this.styleService = styleService;
        this.userService = userService;
    }


    @Override
    public void saveSong(SongAddDTO songAddDTO) {
        Song song = modelMapper.map(songAddDTO, Song.class);
        song.setStyle(this.styleService.findStyleByName(songAddDTO.getStyle()));
        this.songRepository.saveAndFlush(song);
    }

    @Override
    public List<Song> findAllPopSongs() {
        return this.songRepository.findByStyle(this.styleService.findStyleByName(StyleName.POP));
    }

    @Override
    public List<Song> findAllRockSongs() {
        return this.songRepository.findByStyle(this.styleService.findStyleByName(StyleName.ROCK));
    }

    @Override
    public List<Song> findAllJazzSongs() {
        return this.songRepository.findByStyle(this.styleService.findStyleByName(StyleName.JAZZ));
    }

    @Override
    public List<Song> findAllMySongs() {
        return this.userService.findCurrendUser().getPlayList();
    }

    @Override
    public void addToMyPlaylist(Long songId) {
        Optional<Song> song = this.songRepository.findById(songId);
        User user = this.userService.findCurrendUser();
        if (song.isPresent() && !user.getPlayList().contains(song.get())) {
            user.getPlayList().add(song.get());
            this.userService.saveUserWithSongs(user);
        }
    }
}
