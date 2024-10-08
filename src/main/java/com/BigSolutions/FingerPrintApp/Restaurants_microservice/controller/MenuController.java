package com.BigSolutions.FingerPrintApp.Restaurants_microservice.controller;

import com.BigSolutions.FingerPrintApp.Restaurants_microservice.dto.MenuItemDTO;
import com.BigSolutions.FingerPrintApp.Restaurants_microservice.dto.MenuResponseDTO;
import com.BigSolutions.FingerPrintApp.Restaurants_microservice.dto.MenuRequestDTO;
import com.BigSolutions.FingerPrintApp.Restaurants_microservice.mapper.MapperService;
import com.BigSolutions.FingerPrintApp.Restaurants_microservice.service.MenuService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/menu")
public class MenuController {

    private final MenuService menuService;
    private final MapperService mapperService;

    @Autowired
    public MenuController(MenuService menuService, MapperService mapperService) {
        this.menuService = menuService;
        this.mapperService = mapperService;
    }

    @GetMapping
    public ResponseEntity<MenuResponseDTO> getMenuByRestaurantId(@PathVariable String restaurantId) {
        return menuService.getMenuByRestaurantId(restaurantId)
                .map(menu -> ResponseEntity.ok(mapperService.toMenuResponseDTO(menu)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MenuResponseDTO> createOrUpdateMenu(@PathVariable String restaurantId, @Valid @RequestBody MenuRequestDTO menuDTO) {
        var menu = mapperService.toMenuEntity(menuDTO);
        var updatedMenu = menuService.createOrUpdateMenu(restaurantId, menu);
        return ResponseEntity.ok(mapperService.toMenuResponseDTO(updatedMenu));
    }

    @PostMapping("/items")
    public ResponseEntity<MenuItemDTO> addMenuItem(@PathVariable String restaurantId, @Valid @RequestBody MenuItemDTO menuItemDTO) {
        var menuItem = mapperService.toMenuItemEntity(menuItemDTO);
        var addedItem = menuService.addMenuItem(restaurantId, menuItem);
        return new ResponseEntity<>(mapperService.toMenuItemDTO(addedItem), HttpStatus.CREATED);
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<Void> removeMenuItem(@PathVariable String restaurantId, @PathVariable String itemId) {
        menuService.removeMenuItem(restaurantId, itemId);
        return ResponseEntity.noContent().build();
    }
}