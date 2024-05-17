package com.example.supermarket.repository;

import com.example.supermarket.models.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TownRepository extends JpaRepository<Town,String> {
    Town findByName(String name);

}
