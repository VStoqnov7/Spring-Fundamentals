package com.example.reseller.repository;

import com.example.reseller.model.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, String> {

    List<Offer> findAllByUserIdIsNotAndUserIsNotNull(String id);
    List<Offer> findAllByUserUsername(String username);
    @Query("SELECT u.boughtOffers FROM User u WHERE u.id = :userId")
    List<Offer> findBoughtOffersByUserId(String userId);
}