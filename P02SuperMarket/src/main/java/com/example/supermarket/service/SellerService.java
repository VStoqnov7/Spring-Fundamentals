package com.example.supermarket.service;

public interface SellerService {
    void addSeller(String[] details);

    boolean checkSeller(String[] seller);

    void addManager(String[] seller, String[] manager);
}
