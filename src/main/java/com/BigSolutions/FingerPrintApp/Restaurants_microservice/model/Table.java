package com.BigSolutions.FingerPrintApp.Restaurants_microservice.model;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "tables")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Table {
    @Id
    private String id;

    @NotNull
    @Min(1)
    private Integer number;

    @NotNull
    @Min(1)
    private Integer capacity;

    private Boolean isAvailable;
}