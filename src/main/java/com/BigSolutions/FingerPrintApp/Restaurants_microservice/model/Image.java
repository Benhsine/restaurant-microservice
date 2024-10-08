package com.BigSolutions.FingerPrintApp.Restaurants_microservice.model;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "images")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {
    @Id
    private String id;

    @NotBlank
    @URL
    private String url;

    @Size(max = 255)
    private String altText;

    private ImageType type;
}