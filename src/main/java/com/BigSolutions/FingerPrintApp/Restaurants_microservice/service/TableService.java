package com.BigSolutions.FingerPrintApp.Restaurants_microservice.service;

import com.BigSolutions.FingerPrintApp.Restaurants_microservice.model.Table;
import com.BigSolutions.FingerPrintApp.Restaurants_microservice.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TableService {

    private final TableRepository tableRepository;

    @Autowired
    public TableService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public List<Table> getAvailableTablesForRestaurant(String restaurantId) {
        return tableRepository.findByRestaurantIdAndIsAvailable(restaurantId, true);
    }

    public List<Table> getTablesByMinCapacity(Integer minCapacity) {
        return tableRepository.findByCapacityGreaterThanEqual(minCapacity);
    }

    @Transactional
    public Table createTable(Table table) {
        return tableRepository.save(table);
    }

    @Transactional
    public Table updateTableAvailability(String tableId, Boolean isAvailable) {
        return tableRepository.findById(tableId)
                .map(table -> {
                    table.setIsAvailable(isAvailable);
                    return tableRepository.save(table);
                })
                .orElseThrow(() -> new RuntimeException("Table not found with id " + tableId));
    }
}