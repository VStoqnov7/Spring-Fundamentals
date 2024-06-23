package com.paintingscollectors.repository;

import com.paintingscollectors.model.dtos.StyleName;
import com.paintingscollectors.model.entity.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StyleRepository extends JpaRepository<Style, String> {
    Style findByStyleName(StyleName styleName);
}