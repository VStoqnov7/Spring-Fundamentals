package com.example.spotifyPlaylist.service;

import com.example.spotifyPlaylist.model.entity.Style;
import com.example.spotifyPlaylist.model.enums.StyleName;

public interface StyleService {
    void dbInit();

    Style findStyleByName(StyleName styleName);
}
