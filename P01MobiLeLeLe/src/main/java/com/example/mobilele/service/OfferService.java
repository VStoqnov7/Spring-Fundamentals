package com.example.mobilele.service;

import com.example.mobilele.models.dto.OfferDto;
import com.example.mobilele.models.dto.UserLoginDto;

public interface OfferService {
    void addOffer(OfferDto offerDto, UserLoginDto userLoginDto);
}
