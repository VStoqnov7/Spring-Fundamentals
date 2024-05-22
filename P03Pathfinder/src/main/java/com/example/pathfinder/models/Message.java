package com.example.pathfinder.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "messages")
public class Message extends BaseEntity{

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "text_content",columnDefinition = "TEXT")
    private String textContent;

    @ManyToOne
    private User author;

    @ManyToOne
    private User recipient;
}
