package com.example.supermarket.service.impl;

import com.example.supermarket.models.Town;
import com.example.supermarket.models.dto.TownDTO;
import com.example.supermarket.repository.TownRepository;
import com.example.supermarket.service.TownService;
import com.example.supermarket.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;

@Service
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public TownServiceImpl(TownRepository townRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.townRepository = townRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addTown(String name) {
        TownDTO townDTO = new TownDTO();
        townDTO.setName(name);

        if (!validationUtil.isValid(townDTO)){
            this.validationUtil.
                    violations(townDTO)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        }else {
            try{
                this.townRepository.saveAndFlush(this.modelMapper.map(townDTO, Town.class));
                System.out.println("Successfully added town!");
            }catch (Exception e){
                System.out.println("Some thing went wrong");
            }
        }
    }
}
