package com.example.mobilele.web;

import com.example.mobilele.models.entity.Brand;
import com.example.mobilele.models.entity.Offer;
import com.example.mobilele.models.entity.User;
import com.example.mobilele.models.entity.UserRole;
import com.example.mobilele.service.BrandService;
import com.example.mobilele.service.OfferService;
import com.example.mobilele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        List<Brand> brands = brandService.getAllBrands();
        model.addObject("brands", brands);
        model.setViewName("brands");
        return model;
    }

    @GetMapping("/offers/all")
    public ModelAndView allOffer(ModelAndView model){
        List<Offer> offers = offerService.getAllOffers();
        model.addObject("offers", offers);
        model.setViewName("offers");
        return model;
    }

    @GetMapping("/details")
    public ModelAndView details(ModelAndView model){
        model.setViewName("details");
        return model;
    }

    @GetMapping("/update")
    public ModelAndView update(ModelAndView model){
        model.setViewName("update");
        return model;
    }

    @GetMapping("/details/{id}")
    public ModelAndView getOfferDetails(@PathVariable("id") String id, ModelAndView model) {
        Offer offer = offerService.getOfferById(id);
        model.addObject("offer", offer);
        model.setViewName("details");
        return model;
    }

    @GetMapping("/offers/update/{userId}/{offerId}")
    public ModelAndView updateOffer(@PathVariable("userId") String userid,@PathVariable String offerId, ModelAndView model) {
        User user = this.userService.getUserById(userid);
        UserRole role = user.getRole();
        Offer offer = offerService.getOfferById(offerId);
        model.addObject("offer", offer);
        if (user != null && role.getName().name().equals("ADMIN")){
            model.setViewName("update");
        }else {
            model.setViewName("redirect:/offers/all");
        }
        return model;
    }
}
