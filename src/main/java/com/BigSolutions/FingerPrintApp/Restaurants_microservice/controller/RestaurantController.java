package com.BigSolutions.FingerPrintApp.Restaurants_microservice.controller;

import com.BigSolutions.FingerPrintApp.Restaurants_microservice.dto.*;
import com.BigSolutions.FingerPrintApp.Restaurants_microservice.mapper.MapperService;
import com.BigSolutions.FingerPrintApp.Restaurants_microservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    // Assume we have a mapper service to convert between DTOs and entities
    private final MapperService mapperService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, MapperService mapperService) {
        this.restaurantService = restaurantService;
        this.mapperService = mapperService;
    }

    @GetMapping
    public ResponseEntity<List<RestaurantResponseDTO>> getAllRestaurants() {
        List<RestaurantResponseDTO> restaurants = restaurantService.getAllRestaurants().stream()
                .map(mapperService::toRestaurantResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> getRestaurantById(@PathVariable String id) {
        return restaurantService.getRestaurantById(id)
                .map(restaurant -> ResponseEntity.ok(mapperService.toRestaurantResponseDTO(restaurant)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RestaurantResponseDTO> createRestaurant(@Valid @RequestBody RestaurantRequestDTO restaurantDTO) {
        var restaurant = mapperService.toRestaurantEntity(restaurantDTO);
        var createdRestaurant = restaurantService.createRestaurant(restaurant);
        return new ResponseEntity<>(mapperService.toRestaurantResponseDTO(createdRestaurant), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> updateRestaurant(@PathVariable String id, @Valid @RequestBody RestaurantRequestDTO restaurantDTO) {
        var restaurant = mapperService.toRestaurantEntity(restaurantDTO);
        var updatedRestaurant = restaurantService.updateRestaurant(id, restaurant);
        return ResponseEntity.ok(mapperService.toRestaurantResponseDTO(updatedRestaurant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable String id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cuisine/{cuisineType}")
    public ResponseEntity<List<RestaurantResponseDTO>> getRestaurantsByCuisine(@PathVariable String cuisineType) {
        List<RestaurantResponseDTO> restaurants = restaurantService.getRestaurantsByCuisine(cuisineType).stream()
                .map(mapperService::toRestaurantResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(restaurants);
    }
}