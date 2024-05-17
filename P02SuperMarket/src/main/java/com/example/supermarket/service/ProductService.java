package com.example.supermarket.service;

public interface ProductService {
    void addProduct(String[] details);

    void addProductDistribution(String productName, String[] shops);

    boolean checkProduct(String productName);
}
