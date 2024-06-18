package com.example.shopping.controller;

import com.example.shopping.model.dtos.ProductAddDTO;
import com.example.shopping.model.enums.CategoryName;
import com.example.shopping.service.interfaces.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ModelAndView home(ModelAndView model){
        model.setViewName("home");
        return model;
    }

    @ModelAttribute(name = "productAddDTO")
    public ProductAddDTO productAddForm(){
        return new ProductAddDTO();
    }

    @GetMapping("/addProduct")
    public ModelAndView addProduct(ModelAndView model){
        model.addObject("categoriesNameValues", CategoryName.values());
        model.setViewName("product-add");
        return model;
    }

    @PostMapping("/addProduct")
    public ModelAndView addProduct(ModelAndView model,
                                @Valid ProductAddDTO productAddDTO,
                                BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            model.addObject("categoriesNameValues", CategoryName.values());
            model.setViewName("product-add");
            return model;
        }
        this.productService.saveProduct(productAddDTO);
        model.setViewName("redirect:/home");
        return model;
    }
}
