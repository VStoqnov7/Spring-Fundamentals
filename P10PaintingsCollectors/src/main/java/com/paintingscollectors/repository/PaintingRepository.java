package com.paintingscollectors.repository;

import com.paintingscollectors.model.entity.Painting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaintingRepository extends JpaRepository<Painting, String> {

    List<Painting> findAllByOwnerId(String id);
    List<Painting> findAllByOwnerIdNot(String id);
    @Query("SELECT p FROM Painting p ORDER BY p.votes DESC")
    List<Painting> findTop2PaintingsByVotes();
}