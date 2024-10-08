package com.BigSolutions.FingerPrintApp.Restaurants_microservice.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuResponseDTO {
    private String id;
    private List<MenuItemDTO> items;
}
