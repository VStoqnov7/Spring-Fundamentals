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

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    }

    @Override
    public List<Offer> getAllOffers() {
        return this.offerRepository.findAll();
    }

    @Override
    public Offer getOfferById(String id) {
        return this.offerRepository.findById(id).orElse(null);
    }

    @Override
    public void updateOffer(OfferDto updatedOfferDto, String offerId) {
        Optional<Offer> existingOfferOptional = this.offerRepository.findById(offerId);

        if (!existingOfferOptional.isPresent()) {
            throw new EntityNotFoundException("Offer not found with id: " + offerId);
        }

        Offer offerToUpdate = existingOfferOptional.get();

        offerToUpdate.setDescription(updatedOfferDto.getDescription());
        offerToUpdate.setPrice(updatedOfferDto.getPrice());
        offerToUpdate.setImageUrl(updatedOfferDto.getImageUrl());
        offerToUpdate.setModified(LocalDateTime.now());

        Brand brand = offerToUpdate.getModel().getBrand();
        if (brand == null) {
            brand = new Brand();
            offerToUpdate.getModel().setBrand(brand);
        }
        brand.setName(updatedOfferDto.getBrand());
        brand.setCreated(LocalDateTime.now());

        Model model = offerToUpdate.getModel();
        if (model == null) {
            model = new Model();
            offerToUpdate.setModel(model);
        }
        model.setName(updatedOfferDto.getModel());
        model.setCreated(LocalDateTime.now());
        model.setImageUrl(updatedOfferDto.getImageUrl());
        model.setCategory(Category.valueOf(updatedOfferDto.getCategory()));

        this.offerRepository.saveAndFlush(offerToUpdate);
    }

    @Override
    public void deleteOffer(String offerId) {
        this.offerRepository.deleteById(offerId);
    }

}
