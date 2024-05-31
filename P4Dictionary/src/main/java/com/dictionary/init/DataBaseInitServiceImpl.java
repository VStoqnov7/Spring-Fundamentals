package com.dictionary.init;

import com.dictionary.service.LanguageService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class DataBaseInitServiceImpl implements DataBaseInitService{
    private final LanguageService languageService;

    public DataBaseInitServiceImpl(LanguageService languageService) {
        this.languageService = languageService;
    }

    @PostConstruct
    public void init() {
        this.languageService.dbInit();
    }

}
