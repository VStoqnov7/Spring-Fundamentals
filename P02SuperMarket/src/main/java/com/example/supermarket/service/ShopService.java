package com.example.supermarket.service;

import com.example.supermarket.models.dto.ProductViewDTO;
import com.example.supermarket.models.dto.SellerViewDTO;

import java.util.List;

public interface ShopService {
    void addShop(String[] details);

    List<SellerViewDTO> findAllSellersFromShop(String shopName);

    boolean checkShop(String shopName);

    List<ProductViewDTO> findAllProductsFromShop(String shopName);
}
