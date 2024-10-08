package com.BigSolutions.FingerPrintApp.Restaurants_microservice.dto;

import lombok.*;

import java.time.DayOfWeek;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantResponseDTO {
    private String id;
    private String name;
    private String description;
    private String cuisineType;
    private AddressDTO address;
    private Double latitude;
    private Double longitude;
    private String phone;
    private String email;
    private String website;
    private Integer capacity;
    private Map<DayOfWeek, TimeRangeDTO> openingHours;
    private Float averageRating;
    private Boolean isActive;
}
