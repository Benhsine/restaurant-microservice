package com.BigSolutions.FingerPrintApp.Restaurants_microservice.controller;

import com.BigSolutions.FingerPrintApp.Restaurants_microservice.dto.TableRequestDTO;
import com.BigSolutions.FingerPrintApp.Restaurants_microservice.dto.TableResponseDTO;
import com.BigSolutions.FingerPrintApp.Restaurants_microservice.mapper.MapperService;
import com.BigSolutions.FingerPrintApp.Restaurants_microservice.service.TableService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/tables")
public class TableController {

    private final TableService tableService;
    private final MapperService mapperService;

    @Autowired
    public TableController(TableService tableService, MapperService mapperService) {
        this.tableService = tableService;
        this.mapperService = mapperService;
    }

    @GetMapping
    public ResponseEntity<List<TableResponseDTO>> getAvailableTables(@PathVariable String restaurantId) {
        List<TableResponseDTO> tables = tableService.getAvailableTablesForRestaurant(restaurantId).stream()
                .map(mapperService::toTableResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tables);
    }

    @PostMapping
    public ResponseEntity<TableResponseDTO> createTable(@PathVariable String restaurantId, @Valid @RequestBody TableRequestDTO tableDTO) {
        var table = mapperService.toTableEntity(tableDTO);
        var createdTable = tableService.createTable(table);
        return new ResponseEntity<>(mapperService.toTableResponseDTO(createdTable), HttpStatus.CREATED);
    }

    @PutMapping("/{tableId}/availability")
    public ResponseEntity<TableResponseDTO> updateTableAvailability(@PathVariable String restaurantId,
                                                                    @PathVariable String tableId,
                                                                    @RequestParam Boolean isAvailable) {
        var updatedTable = tableService.updateTableAvailability(tableId, isAvailable);
        return ResponseEntity.ok(mapperService.toTableResponseDTO(updatedTable));
    }
}