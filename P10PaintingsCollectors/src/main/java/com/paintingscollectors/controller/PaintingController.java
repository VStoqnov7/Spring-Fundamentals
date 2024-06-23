package com.paintingscollectors.controller;

import com.paintingscollectors.model.dto.PaintingAddDTO;
import com.paintingscollectors.model.dtos.StyleName;
import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.service.interfaces.PaintingService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/home")
public class PaintingController {

    private final PaintingService paintingService;

    public PaintingController(PaintingService paintingService) {
        this.paintingService = paintingService;
    }

    @GetMapping()
    public ModelAndView home(ModelAndView model){
        List<Painting> myPaintings = this.paintingService.findMyPaintings();
        List<Painting> myFavoritesPaintings = this.paintingService.findMyFavoritePaintings();
        List<Painting> otherPaintings = this.paintingService.otherPaintings();
        List<Painting> mostRatedPaintings = this.paintingService.mostRatedPaintings();
        model.addObject("myPaintings", myPaintings);
        model.addObject("myFavoritesPaintings", myFavoritesPaintings);
        model.addObject("otherPaintings", otherPaintings);
        model.addObject("mostRatedPaintings", mostRatedPaintings);

        model.setViewName("home");
        return model;
    }

    @ModelAttribute(name = "paintingAddDTO")
    public PaintingAddDTO paintingAddForm(){
        return new PaintingAddDTO();
    }


    @GetMapping("/addPainting")
    public ModelAndView addPainting(ModelAndView model){
        model.addObject("styleNameValues", StyleName.values());
        model.setViewName("add-painting");
        return model;
    }


    @PostMapping("/addPainting")
    public ModelAndView addPainting(ModelAndView model,
                                   @Valid PaintingAddDTO paintingAddDTO,
                                   BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            model.addObject("styleNameValues", StyleName.values());
            model.setViewName("add-painting");
            return model;
        }
        this.paintingService.savePainting(paintingAddDTO);
        model.setViewName("redirect:/home");
        return model;
    }


    @GetMapping("/removePainting/{paintingId}")
    public ModelAndView removePainting(@PathVariable String paintingId, ModelAndView model){
        this.paintingService.removePainting(paintingId);
        model.setViewName("redirect:/home");
        return model;
    }

    @GetMapping("/addToFavorite/{paintingId}")
    public ModelAndView addToFavorite(@PathVariable String paintingId, ModelAndView model){
        this.paintingService.addToFavorite(paintingId);
        model.setViewName("redirect:/home");
        return model;
    }

    @GetMapping("/removeFavoritePainting/{paintingId}")
    public ModelAndView removeFavoritePanting(@PathVariable String paintingId, ModelAndView model){
        this.paintingService.removeFavoritePanting(paintingId);
        model.setViewName("redirect:/home");
        return model;
    }


    @GetMapping("/addVoteToPanting/{paintingId}")
    public ModelAndView addVoteToPanting(@PathVariable String paintingId, ModelAndView model){
        this.paintingService.addVote(paintingId);
        model.setViewName("redirect:/home");
        return model;
    }
}
