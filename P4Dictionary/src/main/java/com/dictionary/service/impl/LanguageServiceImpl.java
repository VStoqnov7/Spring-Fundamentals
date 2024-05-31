package com.dictionary.service.impl;

import com.dictionary.model.enums.LanguageName;
import com.dictionary.model.entity.Language;
import com.dictionary.repo.LanguageRepository;
import com.dictionary.service.LanguageService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;

    public LanguageServiceImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public void dbInit() {
        if (languageRepository.count() == 0) {
            List<Language> languageDataList = Arrays.asList(
                    new Language(LanguageName.GERMAN, "A West Germanic language, is spoken by over 90 million people worldwide. Known for its complex grammar and compound words, it's the official language of Germany and widely used in Europe."),
                    new Language(LanguageName.SPANISH, "A Romance language, is spoken by over 460 million people worldwide. It boasts a rich history, diverse dialects, and is known for its melodious sound, making it a global cultural treasure."),
                    new Language(LanguageName.FRENCH, "A Romance language spoken worldwide, known for its elegance and cultural richness. It's the official language of France and numerous nations, famed for its cuisine, art, and literature."),
                    new Language(LanguageName.ITALIAN, "A Romance language spoken in Italy and parts of Switzerland, with rich cultural heritage. Known for its melodious sounds, it's a gateway to Italian art, cuisine, and history."));

            this.languageRepository.saveAllAndFlush(languageDataList);
        }
    }
}
