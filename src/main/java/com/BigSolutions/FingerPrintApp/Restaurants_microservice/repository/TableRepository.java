package com.BigSolutions.FingerPrintApp.Restaurants_microservice.repository;

import com.BigSolutions.FingerPrintApp.Restaurants_microservice.model.Table;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends MongoRepository<Table, String> {
    List<Table> findByRestaurantIdAndIsAvailable(String restaurantId, Boolean isAvailable);
    List<Table> findByCapacityGreaterThanEqual(Integer capacity);
}

