package com.dictionary.service;

import com.dictionary.model.entity.Language;
import com.dictionary.model.enums.LanguageName;

public interface LanguageService {
    void dbInit();

    Language findLanguageByName(LanguageName language);
}
