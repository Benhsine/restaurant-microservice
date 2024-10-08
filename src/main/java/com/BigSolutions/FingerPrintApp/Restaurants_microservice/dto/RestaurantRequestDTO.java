package com.BigSolutions.FingerPrintApp.Restaurants_microservice.dto;


import com.BigSolutions.FingerPrintApp.Restaurants_microservice.model.TimeRange;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.time.DayOfWeek;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantRequestDTO {
    @NotBlank
    @Size(max = 100)
    private String name;

    @Size(max = 500)
    private String description;

    @NotBlank
    @Size(max = 50)
    private String cuisineType;

    private AddressDTO address;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @Pattern(regexp = "^\\+?[0-9]{10,14}$")
    private String phone;

    @Email
    private String email;

    @URL
    private String website;

    @Min(1)
    private Integer capacity;

    private Map<DayOfWeek, TimeRangeDTO> openingHours;

    private Boolean isActive;
}