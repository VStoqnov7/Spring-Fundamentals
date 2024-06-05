package com.example.reseller.service;

import com.example.reseller.model.dtos.OfferAddDTO;
import com.example.reseller.model.entity.Offer;

import java.util.List;

public interface OfferService {
    void saveOffer(OfferAddDTO offerAddDTO);

    List<Offer> findAllOtherOffers();

    List<Offer> findAllMyOffers();

    List<Offer> findAllBoughtItems();

    void buyItem(String offerId);

    void removeMyOffer(String offerId);
}
