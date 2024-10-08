package com.BigSolutions.FingerPrintApp.Restaurants_microservice.repository;

import com.BigSolutions.FingerPrintApp.Restaurants_microservice.model.VRFormat;
import com.BigSolutions.FingerPrintApp.Restaurants_microservice.model.VirtualReality;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VirtualRealityRepository extends MongoRepository<VirtualReality, String> {
    Optional<VirtualReality> findByRestaurantId(String restaurantId);
    List<VirtualReality> findByFormat(VRFormat format);
}
