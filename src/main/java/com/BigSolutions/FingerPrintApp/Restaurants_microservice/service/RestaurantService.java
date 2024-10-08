package com.BigSolutions.FingerPrintApp.Restaurants_microservice.service;


import com.BigSolutions.FingerPrintApp.Restaurants_microservice.model.*;
import com.BigSolutions.FingerPrintApp.Restaurants_microservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, MenuRepository menuRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuRepository = menuRepository;
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> getRestaurantById(String id) {
        return restaurantRepository.findById(id);
    }

    public List<Restaurant> getRestaurantsByCuisine(String cuisineType) {
        return restaurantRepository.findByCuisineType(cuisineType);
    }

    public List<Restaurant> getTopRatedRestaurants(Float minRating) {
        return restaurantRepository.findByAverageRatingGreaterThan(minRating);
    }

    @Transactional
    public Restaurant createRestaurant(Restaurant restaurant) {
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        if (restaurant.getMenu() != null) {
            restaurant.getMenu().setId(savedRestaurant.getId());
            menuRepository.save(restaurant.getMenu());
        }
        return savedRestaurant;
    }

    @Transactional
    public Restaurant updateRestaurant(String id, Restaurant restaurantDetails) {
        return restaurantRepository.findById(id)
                .map(restaurant -> {
                    restaurant.setName(restaurantDetails.getName());
                    restaurant.setDescription(restaurantDetails.getDescription());
                    restaurant.setCuisineType(restaurantDetails.getCuisineType());
                    restaurant.setAddress(restaurantDetails.getAddress());
                    restaurant.setLatitude(restaurantDetails.getLatitude());
                    restaurant.setLongitude(restaurantDetails.getLongitude());
                    restaurant.setPhone(restaurantDetails.getPhone());
                    restaurant.setEmail(restaurantDetails.getEmail());
                    restaurant.setWebsite(restaurantDetails.getWebsite());
                    restaurant.setCapacity(restaurantDetails.getCapacity());
                    restaurant.setOpeningHours(restaurantDetails.getOpeningHours());
                    restaurant.setAverageRating(restaurantDetails.getAverageRating());
                    restaurant.setIsActive(restaurantDetails.getIsActive());
                    return restaurantRepository.save(restaurant);
                })
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id " + id));
    }

    public void deleteRestaurant(String id) {
        restaurantRepository.deleteById(id);
    }
}