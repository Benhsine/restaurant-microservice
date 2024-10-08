package com.BigSolutions.FingerPrintApp.Restaurants_microservice.controller;

import com.BigSolutions.FingerPrintApp.Restaurants_microservice.dto.VirtualRealityRequestDTO;
import com.BigSolutions.FingerPrintApp.Restaurants_microservice.dto.VirtualRealityResponseDTO;
import com.BigSolutions.FingerPrintApp.Restaurants_microservice.mapper.MapperService;
import com.BigSolutions.FingerPrintApp.Restaurants_microservice.service.VirtualRealityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/vr-tour")
public class VirtualRealityController {

    private final VirtualRealityService vrService;
    private final MapperService mapperService;

    @Autowired
    public VirtualRealityController(VirtualRealityService vrService, MapperService mapperService) {
        this.vrService = vrService;
        this.mapperService = mapperService;
    }

    @GetMapping
    public ResponseEntity<VirtualRealityResponseDTO> getVirtualRealityByRestaurantId(@PathVariable String restaurantId) {
        return vrService.getVirtualRealityByRestaurantId(restaurantId)
                .map(vr -> ResponseEntity.ok(mapperService.toVirtualRealityResponseDTO(vr)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<VirtualRealityResponseDTO> createOrUpdateVirtualReality(@PathVariable String restaurantId,
                                                                                  @Valid @RequestBody VirtualRealityRequestDTO vrDTO) {
        var vr = mapperService.toVirtualRealityEntity(vrDTO);
        var updatedVR = vrService.createOrUpdateVirtualReality(restaurantId, vr);
        return ResponseEntity.ok(mapperService.toVirtualRealityResponseDTO(updatedVR));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteVirtualReality(@PathVariable String restaurantId) {
        vrService.deleteVirtualReality(restaurantId);
        return ResponseEntity.noContent().build();
    }
}