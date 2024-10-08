package com.BigSolutions.FingerPrintApp.Restaurants_microservice.model;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "menuItems")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItem {
    @Id
    private String id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @Size(max = 500)
    private String description;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;

    @NotBlank
    @Size(max = 50)
    private String category;

    private Boolean isAvailable;

    private List<Image> images;
}