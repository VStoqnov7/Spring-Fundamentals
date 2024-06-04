package com.example.reseller.repository;

import com.example.reseller.model.entity.Condition;
import com.example.reseller.model.enums.ConditionName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionRepository extends JpaRepository<Condition, String> {
    Condition findByConditionName(ConditionName condition);
}