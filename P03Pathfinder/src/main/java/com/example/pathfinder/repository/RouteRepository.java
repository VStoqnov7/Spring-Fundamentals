package com.example.pathfinder.repository;

import com.example.pathfinder.models.Route;
import com.example.pathfinder.models.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, String> {
    @Query("SELECT r FROM Route r LEFT JOIN r.comments c GROUP BY r.id ORDER BY COUNT(c.id) DESC LIMIT 1")
    Route findMostCommentedRoute();

    @Query("SELECT DISTINCT r FROM Route r JOIN r.categories c WHERE c.name = :category")
    List<Route> findAllByCategories(@Param("category") CategoryName category);
}