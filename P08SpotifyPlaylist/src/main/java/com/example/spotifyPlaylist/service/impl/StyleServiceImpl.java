package com.example.spotifyPlaylist.service.impl;

import com.example.spotifyPlaylist.model.entity.Style;
import com.example.spotifyPlaylist.model.enums.StyleName;
import com.example.spotifyPlaylist.repository.StyleRepository;
import com.example.spotifyPlaylist.service.StyleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class StyleServiceImpl implements StyleService {

    private final StyleRepository styleRepository;

    public StyleServiceImpl(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public void dbInit() {
        if (styleRepository.count() == 0) {
            List<Style> stylesDataList = Arrays.asList(
                    new Style(StyleName.POP),
                    new Style(StyleName.ROCK),
                    new Style(StyleName.JAZZ));
            this.styleRepository.saveAllAndFlush(stylesDataList);
        }
    }
}
