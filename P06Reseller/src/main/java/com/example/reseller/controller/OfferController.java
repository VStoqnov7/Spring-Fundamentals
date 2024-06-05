package com.example.reseller.controller;

import com.example.reseller.model.dtos.OfferAddDTO;
import com.example.reseller.model.entity.Offer;
import com.example.reseller.model.entity.User;
import com.example.reseller.model.enums.ConditionName;
import com.example.reseller.service.OfferService;
import com.example.reseller.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class OfferController {

    private final OfferService offerService;
    public final UserService userService;

    public OfferController(OfferService offerService, UserService userService) {
        this.offerService = offerService;
        this.userService = userService;
    }

    @ModelAttribute(name = "offerAddDTO")
    public OfferAddDTO offerAddDTOForm() {
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
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addObject("conditionNameValues", ConditionName.values());
            model.setViewName("offer-add");
            return model;
        }
        this.offerService.saveOffer(offerAddDTO);
        model.setViewName("redirect:/home");
        return model;
    }


    @GetMapping("/buyItem/{offerId}")
    public ModelAndView buyItem(@PathVariable String offerId, ModelAndView model) {
        this.offerService.buyItem(offerId);
        model.setViewName("redirect:/home");
        return model;
    }


    @GetMapping("/removeMyOffer/{offerId}")
    public ModelAndView removeMyOffer(@PathVariable String offerId, ModelAndView model) {
        this.offerService.removeMyOffer(offerId);
        model.setViewName("redirect:/home");
        return model;
    }
}
