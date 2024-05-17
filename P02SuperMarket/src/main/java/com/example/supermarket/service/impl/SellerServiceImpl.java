package com.example.supermarket.service.impl;

import com.example.supermarket.models.Seller;
import com.example.supermarket.models.Shop;
import com.example.supermarket.models.dto.SellerDTO;
import com.example.supermarket.repository.SellerRepository;
import com.example.supermarket.repository.ShopRepository;
import com.example.supermarket.service.SellerService;
import com.example.supermarket.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.math.BigDecimal;

@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final ShopRepository shopRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public SellerServiceImpl(SellerRepository sellerRepository, ShopRepository shopRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.sellerRepository = sellerRepository;
        this.shopRepository = shopRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addSeller(String[] details) {
        SellerDTO sellerDTO = new SellerDTO();
        sellerDTO.setFirstName(details[0]);
        sellerDTO.setLastName(details[1]);
        sellerDTO.setAge(Integer.parseInt(details[2]));
        sellerDTO.setSalary(new BigDecimal(details[3]));
        sellerDTO.setShop(details[4]);

        Shop shop = this.shopRepository.findFirstByName(details[4]);

        if (!validationUtil.isValid(sellerDTO) || shop == null){
            this.validationUtil.
                    violations(sellerDTO)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            if (shop == null){
                System.out.println("The shop does not exist");
            }
        }else {
            try {
                Seller seller = modelMapper.map(sellerDTO,Seller.class);
                seller.setShop(shop);
                this.sellerRepository.saveAndFlush(seller);
                System.out.println("Successfully added seller!");
            }catch (Exception e){
                System.out.println("Some thing went wrong!");
            }
        }

    }

    @Override
    public boolean checkSeller(String[] seller) {
        return this.sellerRepository.findFirstByFirstNameAndLastName(seller[0], seller[1]) != null;
    }

    @Override
    public void addManager(String[] detailsSeller, String[] detailsManager) {
        Seller seller = this.sellerRepository.findFirstByFirstNameAndLastName(detailsSeller[0],detailsSeller[1]);
        Seller manager = this.sellerRepository.findFirstByFirstNameAndLastName(detailsManager[0],detailsManager[1]);
        seller.setManager(manager);
        sellerRepository.saveAndFlush(seller);
        System.out.println("Successfully added manager!");
    }
}
