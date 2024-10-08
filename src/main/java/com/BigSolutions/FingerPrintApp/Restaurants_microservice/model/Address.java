package com.BigSolutions.FingerPrintApp.Restaurants_microservice.model;

import jakarta.validation.constraints.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    @NotBlank
    @Size(max = 100)
    private String street;

    @NotBlank
    @Size(max = 50)
    private String city;

    @NotBlank
    @Size(max = 50)
    private String state;

    @NotBlank
    @Size(max = 10)
    private String zipCode;

    @NotBlank
    @Size(max = 50)
    private String country;
}