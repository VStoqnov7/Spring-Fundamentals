package com.paintingscollectors.service;

import com.paintingscollectors.model.dtos.StyleName;
import com.paintingscollectors.model.entity.Style;
import com.paintingscollectors.repository.StyleRepository;
import com.paintingscollectors.service.interfaces.StyleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class StyleServiceImpl implements StyleService {

    private final StyleRepository styleRepository;

    public StyleServiceImpl(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public void dbInit() {
        if (styleRepository.count() == 0) {
            List<Style> stylesData = Arrays.asList(
                    new Style(StyleName.IMPRESSIONISM,"Impressionism is a painting style most commonly associated with the 19th century where small brush strokes are used to build up a larger picture."),
                    new Style(StyleName.ABSTRACT,"Abstract art does not attempt to represent recognizable subjects in a realistic manner."),
                    new Style(StyleName.EXPRESSIONISM,"Expressionism is a style of art that doesn't concern itself with realism."),
                    new Style(StyleName.SURREALISM,"Surrealism is characterized by dreamlike, fantastical imagery that often defies logical explanation."),
                    new Style(StyleName.REALISM,"Also known as naturalism, this style of art is considered as 'real art' and has been the dominant style of painting since the Renaissance."));
            this.styleRepository.saveAllAndFlush(stylesData);
        }
    }

    @Override
    public Style findStyleByName(StyleName style) {
        return this.styleRepository.findByStyleName(style);
    }

}
