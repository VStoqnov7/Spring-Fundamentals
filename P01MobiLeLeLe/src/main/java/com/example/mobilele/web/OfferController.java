package com.example.mobilele.web;

import com.example.mobilele.models.dto.OfferDto;
import com.example.mobilele.models.dto.UserLoginDto;
import com.example.mobilele.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping("/add")
    public ModelAndView postOffer(ModelAndView model, @Valid OfferDto offerDto, BindingResult bindingResult, HttpSession session){
        if (bindingResult.hasFieldErrors("model")){
            model.addObject("invalidModel",true);
        }
        if (bindingResult.hasFieldErrors("price")){
            model.addObject("invalidPrice",true);
        }
        if (bindingResult.hasFieldErrors("engine")){
            model.addObject("invalidEngine",true);
        }
        if (bindingResult.hasFieldErrors("transmission")){
            model.addObject("invalidTransmission",true);
        }
        if (bindingResult.hasFieldErrors("year")){
            model.addObject("invalidYear",true);
        }
        if (bindingResult.hasFieldErrors("mileage")){
            model.addObject("invalidMileage",true);
        }
        if (bindingResult.hasFieldErrors("description")){
            model.addObject("invalidDescription",true);
        }
        if (bindingResult.hasFieldErrors("imageUrl")){
            model.addObject("invalidUrl",true);
        }
        if (bindingResult.hasFieldErrors("category")){
            model.addObject("invalidCategory",true);
        }
        if (bindingResult.hasFieldErrors("brand")){
            model.addObject("invalidBrand",true);
        }

        if (bindingResult.hasErrors()) {
            model.setViewName("/offer-add");
            return model;
        }

        this.offerService.addOffer(offerDto, (UserLoginDto) session.getAttribute("user"));
        model.setViewName("redirect:/brands/all");
        return model;
    }
}
