package com.dictionary.repo;

import com.dictionary.model.entity.Language;
import com.dictionary.model.entity.Word;
import com.dictionary.model.enums.LanguageName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, String> {
    List<Word> findAllByLanguageLanguageName(LanguageName name);
}