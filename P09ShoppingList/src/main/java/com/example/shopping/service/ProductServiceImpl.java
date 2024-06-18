package com.example.shopping.service;

import com.example.shopping.model.dtos.ProductAddDTO;
import com.example.shopping.model.entity.Product;
import com.example.shopping.repository.ProductRepository;
import com.example.shopping.service.interfaces.CategoryService;
import com.example.shopping.service.interfaces.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveProduct(ProductAddDTO productAddDTO) {
        Product product = modelMapper.map(productAddDTO, Product.class);
        product.setCategory(this.categoryService.findCategoryByName(productAddDTO.getCategory()));
        this.productRepository.saveAndFlush(product);
    }
}
