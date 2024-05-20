package com.example.mobilele.web;

import com.example.mobilele.models.dto.OfferDto;
import com.example.mobilele.models.dto.UserLoginDto;
import com.example.mobilele.models.entity.*;
import com.example.mobilele.models.enums.Engine;
import com.example.mobilele.models.enums.Transmission;
import com.example.mobilele.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping("/add")
    public ModelAndView postOffer(ModelAndView model, @Valid OfferDto offerDto, BindingResult bindingResult, HttpSession session) {
        checkForInvalidFields(model, bindingResult);

        if (bindingResult.hasErrors()) {
            model.setViewName("/offer-add");
            return model;
        }

        this.offerService.addOffer(offerDto, (UserLoginDto) session.getAttribute("user"));
        model.setViewName("redirect:/brands/all");
        return model;
    }

    private static void checkForInvalidFields(ModelAndView model, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors("model")) {
            model.addObject("invalidModel", true);
        }
        if (bindingResult.hasFieldErrors("price")) {
            model.addObject("invalidPrice", true);
        }
        if (bindingResult.hasFieldErrors("engine")) {
            model.addObject("invalidEngine", true);
        }
        if (bindingResult.hasFieldErrors("transmission")) {
            model.addObject("invalidTransmission", true);
        }
        if (bindingResult.hasFieldErrors("year")) {
            model.addObject("invalidYear", true);
        }
        if (bindingResult.hasFieldErrors("mileage")) {
            model.addObject("invalidMileage", true);
        }
        if (bindingResult.hasFieldErrors("description")) {
            model.addObject("invalidDescription", true);
        }
        if (bindingResult.hasFieldErrors("imageUrl")) {
            model.addObject("invalidUrl", true);
        }
        if (bindingResult.hasFieldErrors("category")) {
            model.addObject("invalidCategory", true);
        }
        if (bindingResult.hasFieldErrors("brand")) {
            model.addObject("invalidBrand", true);
        }
    }

    @PostMapping("/update/{offerId}")
    public ModelAndView updateOffer(@PathVariable String offerId,OfferDto offerDto ,ModelAndView modelAndView, HttpSession session, BindingResult bindingResult) {
        checkForInvalidFields(modelAndView, bindingResult);
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("/offers/update/{userId}/{offerId}");
            return modelAndView;
        }

        this.offerService.updateOffer(offerDto,offerId);
        modelAndView.setViewName("redirect:/offers/all");
        return modelAndView;
    }
}
