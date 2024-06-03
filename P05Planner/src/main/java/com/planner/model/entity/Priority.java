package com.planner.model.entity;

import com.planner.model.enums.PriorityName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "priorities")
public class Priority extends BaseEntity{

    @Column(unique = true, nullable = false)
    private PriorityName priorityName;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "priority")
    private List<Task> tasks;
}
