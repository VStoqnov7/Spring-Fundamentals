package com.example.mobilele.service.impl;

import com.example.mobilele.models.dto.OfferDto;
import com.example.mobilele.models.dto.UserLoginDto;
import com.example.mobilele.models.entity.Brand;
import com.example.mobilele.models.entity.Model;
import com.example.mobilele.models.entity.Offer;
import com.example.mobilele.models.entity.User;
import com.example.mobilele.models.enums.Category;
import com.example.mobilele.repository.OfferRepository;
import com.example.mobilele.repository.UserRepository;
import com.example.mobilele.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addOffer(OfferDto offerDto, UserLoginDto userLoginDto) {
        Offer offer = modelMapper.map(offerDto,Offer.class);
        offer.setCreated(LocalDateTime.now());

        Brand brand = new Brand();
        brand.setName(offerDto.getBrand());
        brand.setCreated(LocalDateTime.now());
        Model model = new Model();
        model.setBrand(brand);
        model.setName(offerDto.getModel());
        model.setCreated(LocalDateTime.now());
        model.setImageUrl(offer.getImageUrl());
        model.setCategory(Category.valueOf(offerDto.getCategory()));
        offer.setModel(model);
        User user = this.userRepository.findByUsername(userLoginDto.getUsername());
        offer.setSeller(user);
        this.offerRepository.saveAndFlush(offer);
        System.out.println(offer);

    }
}
