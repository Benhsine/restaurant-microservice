package com.BigSolutions.FingerPrintApp.Restaurants_microservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TableResponseDTO {
    private String id;
    private Integer number;
    private Integer capacity;
    private Boolean isAvailable;
}
