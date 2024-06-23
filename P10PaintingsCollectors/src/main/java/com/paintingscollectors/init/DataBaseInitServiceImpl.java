package com.paintingscollectors.init;

import com.paintingscollectors.service.interfaces.StyleService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DataBaseInitServiceImpl implements DataBaseInitService {

    private final StyleService styleService;

    public DataBaseInitServiceImpl(StyleService styleService) {
        this.styleService = styleService;
    }

    @Override
    @PostConstruct
    public void init() {
        this.styleService.dbInit();
    }
}