package com.likeBook.repository;

import com.likeBook.model.entity.Mood;
import com.likeBook.model.enums.MoodName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoodRepository extends JpaRepository<Mood, String> {

    Mood findByMoodName(MoodName mood);
}