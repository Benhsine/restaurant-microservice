package com.BigSolutions.FingerPrintApp.Restaurants_microservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.URL;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageRequestDTO {
    @NotBlank
    @URL
    private String url;

    @Size(max = 255)
    private String altText;

    private String type;
}
