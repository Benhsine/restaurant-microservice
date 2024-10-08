package com.BigSolutions.FingerPrintApp.Restaurants_microservice.model;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Document(collection = "restaurants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {
    @Id
    private String id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @Size(max = 500)
    private String description;

    @NotBlank
    @Size(max = 50)
    private String cuisineType;

    private Address address;

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

    private Map<DayOfWeek, TimeRange> openingHours;

    private Menu menu;

    @DecimalMin(value = "0.0")
    @DecimalMax(value = "5.0")
    private Float averageRating;

    private List<Table> tables;

    private List<Image> images;

    private VirtualReality vrTour;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private Boolean isActive;

    // Methods can remain the same
}