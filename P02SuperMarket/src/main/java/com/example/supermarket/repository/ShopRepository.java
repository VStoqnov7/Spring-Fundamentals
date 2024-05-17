package com.example.supermarket.repository;

import com.example.supermarket.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop,String> {

    Shop findFirstByName(String name);

}
