package com.likeBook.service;

import com.likeBook.model.entity.Mood;
import com.likeBook.model.enums.MoodName;

public interface MoodService {
    void dbInit();

    Mood findMoodByName(MoodName mood);
}

