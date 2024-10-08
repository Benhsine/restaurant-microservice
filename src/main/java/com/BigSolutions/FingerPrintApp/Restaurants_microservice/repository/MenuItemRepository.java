package com.BigSolutions.FingerPrintApp.Restaurants_microservice.repository;

import com.BigSolutions.FingerPrintApp.Restaurants_microservice.model.MenuItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends MongoRepository<MenuItem, String> {
    List<MenuItem> findByCategory(String category);
    List<MenuItem> findByPriceLessThan(Double price);
}