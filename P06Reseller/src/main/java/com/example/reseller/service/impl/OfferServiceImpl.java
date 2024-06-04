package com.example.reseller.service.impl;

import com.example.reseller.model.dtos.OfferAddDTO;
import com.example.reseller.model.entity.Offer;
import com.example.reseller.model.entity.User;
import com.example.reseller.repository.OfferRepository;
import com.example.reseller.service.ConditionService;
import com.example.reseller.service.OfferService;
import com.example.reseller.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {
    private final ModelMapper modelMapper;
    private final ConditionService conditionService;
    private final OfferRepository offerRepository;
    private final UserService userService;

    public OfferServiceImpl(ModelMapper modelMapper, ConditionService conditionService, OfferRepository offerRepository, UserService userService) {
        this.modelMapper = modelMapper;
        this.conditionService = conditionService;
        this.offerRepository = offerRepository;
        this.userService = userService;
    }

    @Override
    public void saveOffer(OfferAddDTO offerAddDTO) {
        Offer offer = modelMapper.map(offerAddDTO,Offer.class);
        User user = this.userService.findCurrendUser();
        offer.setUser(user);
        offer.setCondition(this.conditionService.findConditionByName(offerAddDTO.getCondition()));
        this.offerRepository.saveAndFlush(offer);
    }

    @Override
    public List<Offer> findAllOtherOffers() {
        return this.offerRepository.findAllByUserIdIsNot(this.userService.findCurrendUser().getId());
    }

    @Override
    public List<Offer> findAllMyOffers() {
        return this.offerRepository.findAllByUserUsername(this.userService.findCurrendUser().getUsername());
    }

    @Override
    public List<Offer> findAllBoughtItems() {
        return this.offerRepository.findBoughtOffersByUserId(this.userService.findCurrendUser().getId());
    }
}
