package com.paintingscollectors.service;

import com.paintingscollectors.model.dto.PaintingAddDTO;
import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.User;
import com.paintingscollectors.repository.PaintingRepository;
import com.paintingscollectors.service.interfaces.PaintingService;
import com.paintingscollectors.service.interfaces.StyleService;
import com.paintingscollectors.service.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaintingServiceImpl implements PaintingService {

    private final PaintingRepository paintingRepository;
    private final StyleService styleService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public PaintingServiceImpl(PaintingRepository paintingRepository, StyleService styleService, ModelMapper modelMapper, UserService userService) {
        this.paintingRepository = paintingRepository;
        this.styleService = styleService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public void savePainting(PaintingAddDTO paintingAddDTO) {
        Painting painting = modelMapper.map(paintingAddDTO, Painting.class);
        painting.setStyle(this.styleService.findStyleByName(paintingAddDTO.getStyle()));
        User currentUser = this.userService.findCurrendUser();
        painting.setOwner(currentUser);
        this.paintingRepository.saveAndFlush(painting);
    }

    @Override
    public List<Painting> findMyPaintings() {
        User user = this.userService.findCurrendUser();
        return this.paintingRepository.findAllByOwnerId(user.getId());
    }

    @Override
    public List<Painting> findMyFavoritePaintings() {
        User user = this.userService.findCurrendUser();
        List<Painting> favoritePaintings = new ArrayList<>(user.getFavoritePaintings());
        return favoritePaintings;
    }

    @Override
    public List<Painting> otherPaintings() {
        User user = this.userService.findCurrendUser();
        return this.paintingRepository.findAllByOwnerIdNot(user.getId());
    }

    @Override
    public List<Painting> mostRatedPaintings() {
        List<Painting> limited2List = this.paintingRepository.findTop2PaintingsByVotes().stream()
                .limit(2)
                .collect(Collectors.toList());
        return limited2List;
    }

    @Override
    public void removePainting(String paintingId) {
        Painting painting = this.paintingRepository.findById(paintingId).orElseThrow(() -> new IllegalArgumentException("Painting not found"));

        boolean isFavorite = userService.findAll().stream()
                .anyMatch(user -> user.getFavoritePaintings().contains(painting));

        if (!isFavorite) {
            List<User> users = userService.findAll();

            users.forEach(user -> {
                if (user.getRatedPaintings().contains(painting)) {
                    user.getRatedPaintings().remove(painting);
                    userService.saveNewUser(user);
                }
            });

            User currentUser = this.userService.findCurrendUser();
            currentUser.getPaintings().remove(painting);
            this.userService.saveNewUser(currentUser);

            this.paintingRepository.delete(painting);
        }
    }

    @Override
    public void addToFavorite(String paintingId) {
        User user = this.userService.findCurrendUser();
        Painting painting = this.paintingRepository.findById(paintingId).get();
        if (!user.getFavoritePaintings().contains(painting)) {
            user.getFavoritePaintings().add(painting);
            this.userService.saveNewUser(user);
        }
    }

    @Override
    public void removeFavoritePanting(String paintingId) {
        User user = this.userService.findCurrendUser();
        Painting painting = this.paintingRepository.findById(paintingId).get();
        user.getFavoritePaintings().remove(painting);
        this.userService.saveNewUser(user);
    }

    @Override
    public void addVote(String paintingId) {
        User user = this.userService.findCurrendUser();
        Painting painting = this.paintingRepository.findById(paintingId).get();

        user.getRatedPaintings().add(painting);
        painting.setVotes(painting.getVotes() + 1);
        this.userService.saveNewUser(user);
    }

}
