package com.BigSolutions.FingerPrintApp.Restaurants_microservice.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuRequestDTO {
    private List<MenuItemDTO> items;
}