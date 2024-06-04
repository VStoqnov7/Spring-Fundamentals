package com.example.reseller.model.entity;

import com.example.reseller.model.enums.ConditionName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "conditions")
public class Condition extends BaseEntity{

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private ConditionName conditionName;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "condition", cascade = CascadeType.ALL)
    private List<Offer> offers;

    public Condition(ConditionName conditionName, String description) {
        this.conditionName = conditionName;
        this.description = description;
    }

    public Condition() {
    }
}
