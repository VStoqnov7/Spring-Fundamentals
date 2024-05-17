package com.example.supermarket.service.impl;

import com.example.supermarket.models.Category;
import com.example.supermarket.models.dto.CategoryDTO;
import com.example.supermarket.repository.CategoryRepository;
import com.example.supermarket.service.CategoryService;
import com.example.supermarket.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addCategory(String name) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(name);

        if (!this.validationUtil.isValid(categoryDTO)) {
            this.validationUtil.
                    violations(categoryDTO)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        } else {
            try {
                this.categoryRepository
                        .saveAndFlush(this.modelMapper.map(categoryDTO, Category.class));
                System.out.println("Successfully added category!");
            } catch (Exception e) {
                System.out.println("Some thing went wrong!");
            }
        }

    }
}
