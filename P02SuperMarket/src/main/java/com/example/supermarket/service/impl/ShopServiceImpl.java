package com.example.supermarket.service.impl;

import com.example.supermarket.models.Shop;
import com.example.supermarket.models.Town;
import com.example.supermarket.models.dto.ProductViewDTO;
import com.example.supermarket.models.dto.SellerViewDTO;
import com.example.supermarket.models.dto.ShopDTO;
import com.example.supermarket.repository.ShopRepository;
import com.example.supermarket.repository.TownRepository;
import com.example.supermarket.service.ShopService;
import com.example.supermarket.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final TownRepository townRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public ShopServiceImpl(ShopRepository shopRepository, TownRepository townRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.shopRepository = shopRepository;
        this.townRepository = townRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addShop(String[] details) {

        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setName(details[0]);
        shopDTO.setAddress(details[1]);
        shopDTO.setTown(details[2]);

        Town town = this.townRepository.findByName(details[2]);


        if (!validationUtil.isValid(shopDTO) || town == null){
            this.validationUtil.
                    violations(shopDTO)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            if (town == null){
                System.out.println("The city does not exist");
            }

        }else {
            try {
                Shop shop = this.modelMapper.map(shopDTO, Shop.class);
                shop.setTown(town);
                this.shopRepository.saveAndFlush(shop);
                System.out.println("Successfully added shop!");
            }catch (Exception e){
                System.out.println("Some thing went wrong!");
            }
        }

    }

    @Transactional
    @Override
    public List<SellerViewDTO> findAllSellersFromShop(String shopName) {
        return this.shopRepository
                .findFirstByName(shopName)
                .getSellers()
                .stream()
                .map(seller -> this.modelMapper.map(seller, SellerViewDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean checkShop(String shopName) {
        return this.shopRepository.findFirstByName(shopName) != null;
    }

    @Transactional
    @Override
    public List<ProductViewDTO> findAllProductsFromShop(String shopName) {
        return this.shopRepository
                .findFirstByName(shopName)
                .getProducts()
                .stream()
                .map(product -> this.modelMapper.map(product, ProductViewDTO.class))
                .collect(Collectors.toList());
    }
}
