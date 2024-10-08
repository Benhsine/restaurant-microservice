package com.BigSolutions.FingerPrintApp.Restaurants_microservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.URL;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VirtualRealityRequestDTO {
    @NotBlank
    @URL
    private String url;

    private String format;
}
