package com.BigSolutions.FingerPrintApp.Restaurants_microservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeRangeDTO {
    @NotNull
    private LocalTime openTime;

    @NotNull
    private LocalTime closeTime;
}

