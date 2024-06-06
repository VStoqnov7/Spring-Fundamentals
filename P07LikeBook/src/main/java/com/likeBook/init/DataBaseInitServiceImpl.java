package com.likeBook.init;

import com.likeBook.service.MoodService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class DataBaseInitServiceImpl implements DataBaseInitService {
    private final MoodService moodService;

    public DataBaseInitServiceImpl(MoodService moodService) {
        this.moodService = moodService;
    }

    @PostConstruct
    public void init() {
        this.moodService.dbInit();
    }
}