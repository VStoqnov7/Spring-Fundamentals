package com.example.mobilele.service;

import com.example.mobilele.models.dto.OfferDto;
import com.example.mobilele.models.dto.UserLoginDto;
import com.example.mobilele.models.entity.Offer;

import java.util.List;

public interface OfferService {
    void addOffer(OfferDto offerDto, UserLoginDto userLoginDto);

}
