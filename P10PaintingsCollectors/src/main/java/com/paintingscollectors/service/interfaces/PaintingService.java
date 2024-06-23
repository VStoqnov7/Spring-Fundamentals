package com.paintingscollectors.service.interfaces;

import com.paintingscollectors.model.dto.PaintingAddDTO;
import com.paintingscollectors.model.entity.Painting;

import java.util.List;

public interface PaintingService {
    void savePainting(PaintingAddDTO paintingAddDTO);

    List<Painting> findMyPaintings();

    List<Painting> findMyFavoritePaintings();

    List<Painting> otherPaintings();

    List<Painting> mostRatedPaintings();

    void removePainting(String paintingId);

    void addToFavorite(String paintingId);

    void removeFavoritePanting(String paintingId);

    void addVote(String paintingId);
}
