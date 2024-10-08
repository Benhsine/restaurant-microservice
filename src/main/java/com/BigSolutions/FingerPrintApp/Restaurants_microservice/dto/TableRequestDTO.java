package com.BigSolutions.FingerPrintApp.Restaurants_microservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TableRequestDTO {
    @NotNull
    @Min(1)
    private Integer number;

    @NotNull
    @Min(1)
    private Integer capacity;

    private Boolean isAvailable;
}
