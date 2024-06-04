package com.example.reseller.service;

import com.example.reseller.model.entity.Condition;
import com.example.reseller.model.enums.ConditionName;

public interface ConditionService {
    void dbInit();

    Condition findConditionByName(ConditionName condition);
}
