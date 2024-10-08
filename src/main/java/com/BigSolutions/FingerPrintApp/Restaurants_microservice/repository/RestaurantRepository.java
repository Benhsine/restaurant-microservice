package com.BigSolutions.FingerPrintApp.Restaurants_microservice.repository;


import com.BigSolutions.FingerPrintApp.Restaurants_microservice.model.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
    List<Restaurant> findByCuisineType(String cuisineType);
    List<Restaurant> findByAverageRatingGreaterThan(Float rating);
    Optional<Restaurant> findByName(String name);
}