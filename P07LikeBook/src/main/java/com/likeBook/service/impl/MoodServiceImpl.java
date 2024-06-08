package com.likeBook.service.impl;

import com.likeBook.model.entity.Mood;
import com.likeBook.model.enums.MoodName;
import com.likeBook.repository.MoodRepository;
import com.likeBook.service.MoodService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MoodServiceImpl implements MoodService {
    private final MoodRepository moodRepository;

    public MoodServiceImpl(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    @Override
    public void dbInit() {
        if (moodRepository.count() == 0) {
            List<Mood> prioritiesDataList = Arrays.asList(
                    new Mood(MoodName.HAPPY),
                    new Mood(MoodName.SAD),
                    new Mood(MoodName.INSPIRED));
            this.moodRepository.saveAllAndFlush(prioritiesDataList);
        }
    }

    @Override
    public Mood findMoodByName(MoodName mood) {
        return this.moodRepository.findByMoodName(mood);
    }
}
