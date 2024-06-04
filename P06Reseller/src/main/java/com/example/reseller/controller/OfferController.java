package com.example.reseller.controller;

import com.example.reseller.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("offerAdd")
    public ModelAndView home(ModelAndView model) {
        model.setViewName("offer-add");
        return model;
    }
}
