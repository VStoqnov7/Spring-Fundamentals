package com.example.reseller.init;

import com.example.reseller.service.ConditionService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class DataBaseInitServiceImpl implements DataBaseInitService {
    private final ConditionService conditionService;

    public DataBaseInitServiceImpl(ConditionService conditionService) {
        this.conditionService = conditionService;
    }

    @PostConstruct
    public void init() {
        this.conditionService.dbInit();
    }
}