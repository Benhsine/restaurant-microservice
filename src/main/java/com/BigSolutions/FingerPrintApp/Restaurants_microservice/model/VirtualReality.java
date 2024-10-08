package com.BigSolutions.FingerPrintApp.Restaurants_microservice.model;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "virtualReality")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VirtualReality {
    @Id
    private String id;

    @NotBlank
    @URL
    private String url;

    private VRFormat format;
}