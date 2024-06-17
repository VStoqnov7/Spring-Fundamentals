package com.dictionary.controller;



import com.dictionary.model.entity.Word;
import com.dictionary.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private final WordService wordService;

    public HomeController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/home")
    public ModelAndView home(ModelAndView model){
        List<Word> germanWords = this.wordService.findAllGermanLanguage();
        List<Word> spanishWords = this.wordService.findAllSpanishLanguage();
        List<Word> frenchWords = this.wordService.findAllFrenchLanguage();
        List<Word> italianWords = this.wordService.findAllItalianLanguage();
        int allWordsCount = germanWords.size() + spanishWords.size() + frenchWords.size() + italianWords.size();

        model.addObject("germanWords",germanWords);
        model.addObject("spanishWords",spanishWords);
        model.addObject("frenchWords",frenchWords);
        model.addObject("italianWords",italianWords);
        model.addObject("allWordsCount", allWordsCount);
        model.setViewName("home");
        return model;
    }
}
