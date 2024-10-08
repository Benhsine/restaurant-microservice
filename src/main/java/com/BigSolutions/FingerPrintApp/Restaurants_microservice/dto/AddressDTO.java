package com.BigSolutions.FingerPrintApp.Restaurants_microservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {
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
