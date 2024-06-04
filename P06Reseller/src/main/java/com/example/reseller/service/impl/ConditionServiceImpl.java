package com.example.reseller.service.impl;

import com.example.reseller.model.entity.Condition;
import com.example.reseller.model.enums.ConditionName;
import com.example.reseller.repository.ConditionRepository;
import com.example.reseller.service.ConditionService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ConditionServiceImpl implements ConditionService {
    private final ConditionRepository conditionRepository;

    public ConditionServiceImpl(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    @Override
    public void dbInit() {
        if (conditionRepository.count() == 0) {
            List<Condition> conditionsDataList = Arrays.asList(
                    new Condition(ConditionName.EXCELLENT, "In perfect condition"),
                    new Condition(ConditionName.GOOD, "Some signs of wear and tear or minor defects"),
                    new Condition(ConditionName.ACCEPTABLE, "The item is fairly worn but continues to function properly"));
            this.conditionRepository.saveAllAndFlush(conditionsDataList);
        }
    }
}
