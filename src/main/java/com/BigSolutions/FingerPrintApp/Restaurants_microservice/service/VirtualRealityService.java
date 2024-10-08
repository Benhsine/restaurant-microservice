package com.BigSolutions.FingerPrintApp.Restaurants_microservice.service;

import com.BigSolutions.FingerPrintApp.Restaurants_microservice.model.*;
import com.BigSolutions.FingerPrintApp.Restaurants_microservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class VirtualRealityService {

    private final VirtualRealityRepository virtualRealityRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public VirtualRealityService(VirtualRealityRepository virtualRealityRepository, RestaurantRepository restaurantRepository) {
        this.virtualRealityRepository = virtualRealityRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Optional<VirtualReality> getVirtualRealityByRestaurantId(String restaurantId) {
        return virtualRealityRepository.findByRestaurantId(restaurantId);
    }

    public List<VirtualReality> getVirtualRealitiesByFormat(VRFormat format) {
        return virtualRealityRepository.findByFormat(format);
    }

    @Transactional
    public VirtualReality createOrUpdateVirtualReality(String restaurantId, VirtualReality virtualReality) {
        return restaurantRepository.findById(restaurantId)
                .map(restaurant -> {
                    virtualReality.setId(restaurant.getVrTour() != null ? restaurant.getVrTour().getId() : null);
                    VirtualReality savedVR = virtualRealityRepository.save(virtualReality);
                    restaurant.setVrTour(savedVR);
                    restaurantRepository.save(restaurant);
                    return savedVR;
                })
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id " + restaurantId));
    }

    @Transactional
    public void deleteVirtualReality(String restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id " + restaurantId));

        if (restaurant.getVrTour() != null) {
            String vrId = restaurant.getVrTour().getId();
            restaurant.setVrTour(null);
            restaurantRepository.save(restaurant);
            virtualRealityRepository.deleteById(vrId);
        }
    }
}