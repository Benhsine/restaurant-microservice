package com.BigSolutions.FingerPrintApp.Restaurants_microservice.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeRange {
    @NotNull
    private LocalTime openTime;

    @NotNull
    private LocalTime closeTime;
}
