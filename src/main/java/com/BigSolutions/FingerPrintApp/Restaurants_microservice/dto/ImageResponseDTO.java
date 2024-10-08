package com.BigSolutions.FingerPrintApp.Restaurants_microservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageResponseDTO {
    private String id;
    private String url;
    private String altText;
    private String type;
}
