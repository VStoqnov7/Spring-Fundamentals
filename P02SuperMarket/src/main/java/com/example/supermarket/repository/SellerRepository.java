package com.example.supermarket.repository;

import com.example.supermarket.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,String> {
    Seller findFirstByFirstNameAndLastName(String firstName, String lastName);

}
