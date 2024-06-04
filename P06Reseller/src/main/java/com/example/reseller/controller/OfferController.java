package com.example.reseller.controller;

import com.example.reseller.model.dtos.OfferAddDTO;
import com.example.reseller.model.entity.Offer;
import com.example.reseller.model.enums.ConditionName;
import com.example.reseller.service.OfferService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/home")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @ModelAttribute(name = "offerAddDTO")
    public OfferAddDTO offerAddDTOForm(){
        return new OfferAddDTO();
    }

    @GetMapping("offerAdd")
    public ModelAndView offerAdd(ModelAndView model) {
        model.addObject("conditionNameValues", ConditionName.values());
        model.setViewName("offer-add");
        return model;
    }

    @PostMapping("/offerAdd")
    public ModelAndView offerAdd(ModelAndView model,
                                 @Valid OfferAddDTO offerAddDTO,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            model.addObject("conditionNameValues", ConditionName.values());
            model.setViewName("offer-add");
            return model;
        }
        this.offerService.saveOffer(offerAddDTO);
        model.setViewName("redirect:/home");
        return model;
    }
}
