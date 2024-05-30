package com.example.pathfinder.repository;

import com.example.pathfinder.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, String> {
    @Query("SELECT r FROM Route r LEFT JOIN r.comments c GROUP BY r.id ORDER BY COUNT(c.id) DESC LIMIT 1")
    Route findMostCommentedRoute();
}