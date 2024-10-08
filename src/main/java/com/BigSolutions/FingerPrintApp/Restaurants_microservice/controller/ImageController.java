package com.BigSolutions.FingerPrintApp.Restaurants_microservice.controller;

import com.BigSolutions.FingerPrintApp.Restaurants_microservice.dto.ImageRequestDTO;
import com.BigSolutions.FingerPrintApp.Restaurants_microservice.dto.ImageResponseDTO;
import com.BigSolutions.FingerPrintApp.Restaurants_microservice.mapper.MapperService;
import com.BigSolutions.FingerPrintApp.Restaurants_microservice.service.ImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/images")
public class ImageController {

    private final ImageService imageService;
    private final MapperService mapperService;

    @Autowired
    public ImageController(ImageService imageService, MapperService mapperService) {
        this.imageService = imageService;
        this.mapperService = mapperService;
    }

    @GetMapping
    public ResponseEntity<List<ImageResponseDTO>> getImagesByRestaurantId(@PathVariable String restaurantId) {
        List<ImageResponseDTO> images = imageService.getImagesByRestaurantId(restaurantId).stream()
                .map(mapperService::toImageResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(images);
    }

    @PostMapping
    public ResponseEntity<ImageResponseDTO> addImageToRestaurant(@PathVariable String restaurantId, @Valid @RequestBody ImageRequestDTO imageDTO) {
        var image = mapperService.toImageEntity(imageDTO);
        var addedImage = imageService.addImageToRestaurant(restaurantId, image);
        return new ResponseEntity<>(mapperService.toImageResponseDTO(addedImage), HttpStatus.CREATED);
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> removeImageFromRestaurant(@PathVariable String restaurantId, @PathVariable String imageId) {
        imageService.removeImageFromRestaurant(restaurantId, imageId);
        return ResponseEntity.noContent().build();
    }
}