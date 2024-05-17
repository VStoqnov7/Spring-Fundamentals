package com.example.supermarket.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "shops")
public class Shop {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private String id;

    @Column(nullable = false, unique = true)
    private String address;

    private String name;

    @ManyToOne
    private Town town;

    @OneToMany(mappedBy = "shop")
    private List<Seller> sellers;

    @ManyToMany(mappedBy = "shops")
    private List<Product> products;
}
