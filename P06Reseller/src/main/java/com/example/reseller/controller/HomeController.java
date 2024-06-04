package com.example.reseller.controller;

import com.example.reseller.model.entity.Offer;
import com.example.reseller.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final OfferService offerService;

    public HomeController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping
    public ModelAndView home(ModelAndView model) {
        List<Offer> otherOffers = this.offerService.findAllOtherOffers();
        List<Offer> myOffers = this.offerService.findAllMyOffers();
        List<Offer> boughtItems = this.offerService.findAllBoughtItems();
        model.addObject("otherOffers",otherOffers);
        model.addObject("myOffers",myOffers);
        model.addObject("boughtItems",boughtItems);
        model.setViewName("home");
        return model;
    }
}
