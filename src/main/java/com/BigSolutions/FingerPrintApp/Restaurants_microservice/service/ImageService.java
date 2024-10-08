package com.BigSolutions.FingerPrintApp.Restaurants_microservice.service;

import com.BigSolutions.FingerPrintApp.Restaurants_microservice.model.*;
import com.BigSolutions.FingerPrintApp.Restaurants_microservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository, RestaurantRepository restaurantRepository) {
        this.imageRepository = imageRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<Image> getImagesByRestaurantId(String restaurantId) {
        return imageRepository.findByRestaurantId(restaurantId);
    }

    public List<Image> getImagesByType(ImageType type) {
        return imageRepository.findByType(type);
    }

    @Transactional
    public Image addImageToRestaurant(String restaurantId, Image image) {
        return restaurantRepository.findById(restaurantId)
                .map(restaurant -> {
                    image.setId(null); // Ensure a new image is created
                    Image savedImage = imageRepository.save(image);
                    restaurant.getImages().add(savedImage);
                    restaurantRepository.save(restaurant);
                    return savedImage;
                })
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id " + restaurantId));
    }

    @Transactional
    public void removeImageFromRestaurant(String restaurantId, String imageId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id " + restaurantId));

        restaurant.getImages().removeIf(image -> image.getId().equals(imageId));
        restaurantRepository.save(restaurant);
        imageRepository.deleteById(imageId);
    }
}