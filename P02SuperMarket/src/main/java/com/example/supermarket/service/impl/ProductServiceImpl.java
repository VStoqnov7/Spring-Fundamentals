package com.example.supermarket.service.impl;

import com.example.supermarket.models.Category;
import com.example.supermarket.models.Product;
import com.example.supermarket.models.Shop;
import com.example.supermarket.models.dto.ProductDTO;
import com.example.supermarket.repository.CategoryRepository;
import com.example.supermarket.repository.ProductRepository;
import com.example.supermarket.repository.ShopRepository;
import com.example.supermarket.service.ProductService;
import com.example.supermarket.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ShopRepository shopRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ShopRepository shopRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.shopRepository = shopRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addProduct(String[] details) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(details[0]);
        productDTO.setPrice(new BigDecimal(details[1]));
        productDTO.setBestBefore(LocalDate.parse(details[2], formatter));
        productDTO.setCategory(details[3]);

        Category category = this.categoryRepository.findFirstByName(details[3]);


        if (!validationUtil.isValid(productDTO) || category == null) {
            this.validationUtil.
                    violations(productDTO)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            if (category == null) {
                System.out.println("The category does not exist");
            }
        } else {
            try {
                Product product = modelMapper.map(productDTO, Product.class);
                product.setCategory(category);
                this.productRepository.saveAndFlush(product);
                System.out.println("Successfully added product!");
            }catch (Exception e){
                System.out.println("Some thing went wrong!");
            }
        }
    }

    @Transactional
    @Override
    public void addProductDistribution(String productName, String[] shops) {
        Product product = this.productRepository
                .findFirstByName(productName);

        Arrays.stream(shops)
                .forEach(s -> {
                    Shop shop = this.shopRepository.findFirstByName(s);
                    if (shop != null){
                    product.getShops()
                            .add(shop);
                        System.out.println("Successfully added product distribution!");
                    }else {
                        System.out.println("Invalid shop");
                    }
                });

        this.productRepository.saveAndFlush(product);
    }

    @Override
    public boolean checkProduct(String productName) {
        Product product = this.productRepository.findFirstByName(productName);
        return product != null;
    }
}
