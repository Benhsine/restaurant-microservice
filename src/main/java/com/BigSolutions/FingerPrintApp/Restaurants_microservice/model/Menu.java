package com.BigSolutions.FingerPrintApp.Restaurants_microservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "menus")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menu {
    @Id
    private String id;

    private List<MenuItem> items;
}
