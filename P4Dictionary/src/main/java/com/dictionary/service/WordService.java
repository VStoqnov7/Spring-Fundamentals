package com.dictionary.service;

import com.dictionary.model.dto.WordAddDTO;
import com.dictionary.model.entity.Language;
import com.dictionary.model.entity.Word;

import java.util.List;

public interface WordService {
    void saveWord(WordAddDTO wordAddDTO);

    List<Word> findAllGermanLanguage();

    List<Word> findAllSpanishLanguage();

    List<Word> findAllFrenchLanguage();

    List<Word> findAllItalianLanguage();
}