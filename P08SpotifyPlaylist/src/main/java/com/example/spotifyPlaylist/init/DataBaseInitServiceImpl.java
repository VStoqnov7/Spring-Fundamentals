package com.example.spotifyPlaylist.init;

import com.example.spotifyPlaylist.service.StyleService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class DataBaseInitServiceImpl implements DataBaseInitService {
    private final StyleService styleService;

    public DataBaseInitServiceImpl(StyleService styleService) {
        this.styleService = styleService;
    }

    @PostConstruct
    public void init() {
        this.styleService.dbInit();
    }
}