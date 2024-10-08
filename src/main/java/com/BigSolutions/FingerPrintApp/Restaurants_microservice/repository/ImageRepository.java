package com.BigSolutions.FingerPrintApp.Restaurants_microservice.repository;

import com.BigSolutions.FingerPrintApp.Restaurants_microservice.model.Image;
import com.BigSolutions.FingerPrintApp.Restaurants_microservice.model.ImageType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ImageRepository extends MongoRepository<Image, String> {
    List<Image> findByType(ImageType type);
    List<Image> findByRestaurantId(String restaurantId);
}

