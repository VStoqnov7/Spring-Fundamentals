package com.dictionary.controller;

import com.dictionary.model.dto.UserRegistrationDTO;
import com.dictionary.model.dto.WordAddDTO;
import com.dictionary.model.entity.Language;
import com.dictionary.model.enums.LanguageName;
import com.dictionary.service.WordService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class WordController {

    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @ModelAttribute("wordAddDTO")
    private WordAddDTO wordAddForm(){
        return new WordAddDTO();
    }

    @GetMapping("/wordAdd")
    public ModelAndView register(ModelAndView model){
        model.addObject("languageValues", LanguageName.values());
        model.setViewName("word-add");
        return model;
    }

    @PostMapping("/wordAdd")
    public ModelAndView register(ModelAndView model,
                                 @Valid WordAddDTO wordAddDTO,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            model.addObject("languageValues", LanguageName.values());
            model.setViewName("word-add");
            return model;
        }

        this.wordService.saveWord(wordAddDTO);

        model.setViewName("redirect:/home");
        return model;
    }

    @GetMapping("/removeWord/{wordId}")
    public ModelAndView removeWord(@PathVariable String wordId, ModelAndView model){
        this.wordService.removeWord(wordId);
        model.setViewName("redirect:/home");
        return model;
    }

    @GetMapping("/removeAllWords")
    public ModelAndView removeAllWords(ModelAndView model){
        this.wordService.removeAllWords();
        model.setViewName("redirect:/home");
        return model;
    }
}
