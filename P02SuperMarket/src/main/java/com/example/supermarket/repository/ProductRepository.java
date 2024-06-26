package com.example.supermarket.repository;

import com.example.supermarket.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {

    Product findFirstByName(String name);
}
