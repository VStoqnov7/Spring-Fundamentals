package com.example.mobilele.web;

import com.example.mobilele.models.entity.Brand;
import com.example.mobilele.service.BrandService;
import com.example.mobilele.service.OfferService;
import com.example.mobilele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private final UserService userService;
    private final OfferService offerService;
    private final BrandService brandService;

    public HomeController(UserService userService, OfferService offerService, BrandService brandService) {
        this.userService = userService;
        this.offerService = offerService;
        this.brandService = brandService;
    }

    @GetMapping
    public ModelAndView getHome(ModelAndView model) {
        model.setViewName("index");

        return model;
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView model){
        model.setViewName("auth-login");
        return model;
    }

    @GetMapping("/register")
    public ModelAndView register(ModelAndView model){
        model.setViewName("auth-register");
        return model;
    }

    @GetMapping("/offers/add")
    public ModelAndView offersAdd(ModelAndView model){
        model.setViewName("offer-add");
        return model;
    }

    @GetMapping("/brands/all")
    public ModelAndView allBrands(ModelAndView model){
        boolean areImported = this.userService.areImported();
        model.addObject("areImported", areImported);
        List<Brand> brands = brandService.getAllBrands();
        model.addObject("brands", brands);
        model.setViewName("brands");
        return model;
    }

    @GetMapping("/offers/all")
    public ModelAndView allOffer(ModelAndView model){
        model.setViewName("offers");
        return model;
    }
}
