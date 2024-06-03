package com.dictionary.service.impl;

import com.dictionary.model.dto.WordAddDTO;
import com.dictionary.model.entity.Language;
import com.dictionary.model.entity.User;
import com.dictionary.model.entity.Word;
import com.dictionary.model.enums.LanguageName;
import com.dictionary.repo.WordRepository;
import com.dictionary.service.LanguageService;
import com.dictionary.service.UserService;
import com.dictionary.service.WordService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class WordServiceImpl implements WordService {
    private final WordRepository wordRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final LanguageService languageService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public WordServiceImpl(WordRepository wordRepository, ModelMapper modelMapper, UserService userService, LanguageService languageService) {
        this.wordRepository = wordRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.languageService = languageService;
    }

    @Override
    public void saveWord(WordAddDTO wordAddDTO) {
        User user = userService.findCurrendUser();
        Word word = modelMapper.map(wordAddDTO, Word.class);
        word.setLanguage(this.languageService.findLanguageByName(wordAddDTO.getLanguage()));
        word.setAddedBy(user);
        this.wordRepository.saveAndFlush(word);
    }

    @Override
    public List<Word> findAllGermanLanguage() {
        return this.wordRepository.findAllByLanguageLanguageName(LanguageName.GERMAN);
    }

    @Override
    public List<Word> findAllSpanishLanguage() {
        return this.wordRepository.findAllByLanguageLanguageName(LanguageName.SPANISH);
    }

    @Override
    public List<Word> findAllFrenchLanguage() {
        return this.wordRepository.findAllByLanguageLanguageName(LanguageName.FRENCH);
    }

    @Override
    public List<Word> findAllItalianLanguage() {
        return this.wordRepository.findAllByLanguageLanguageName(LanguageName.ITALIAN);
    }

    @Override
    public void removeWord(String wordId) {
        Word wordById = this.wordRepository.findById(wordId).orElse(null);

        if (wordById != null){
            this.wordRepository.delete(wordById);
        }
    }

    @Override
    public void removeAllWords() {
        this.wordRepository.deleteAll();
    }
}
