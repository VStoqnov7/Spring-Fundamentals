package com.example.pathfinder.service;

import com.example.pathfinder.models.dto.PictureUploadDTO;

public interface PictureService {
    void uploadPicture(String routeId, PictureUploadDTO pictureUploadDTO);
}
