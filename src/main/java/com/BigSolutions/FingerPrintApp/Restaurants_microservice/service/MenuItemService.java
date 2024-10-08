package com.BigSolutions.FingerPrintApp.Restaurants_microservice.service;

import com.BigSolutions.FingerPrintApp.Restaurants_microservice.model.*;
import com.BigSolutions.FingerPrintApp.Restaurants_microservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final MenuRepository menuRepository;

    @Autowired
    public MenuItemService(MenuItemRepository menuItemRepository, MenuRepository menuRepository) {
        this.menuItemRepository = menuItemRepository;
        this.menuRepository = menuRepository;
    }

    public List<MenuItem> getMenuItemsByCategory(String category) {
        return menuItemRepository.findByCategory(category);
    }

    public List<MenuItem> getMenuItemsBelowPrice(Double maxPrice) {
        return menuItemRepository.findByPriceLessThan(maxPrice);
    }

    @Transactional
    public MenuItem createMenuItem(String menuId, MenuItem menuItem) {
        return menuRepository.findById(menuId)
                .map(menu -> {
                    menuItem.setId(null); // Ensure a new item is created
                    MenuItem savedItem = menuItemRepository.save(menuItem);
                    menu.getItems().add(savedItem);
                    menuRepository.save(menu);
                    return savedItem;
                })
                .orElseThrow(() -> new RuntimeException("Menu not found with id " + menuId));
    }

    @Transactional
    public MenuItem updateMenuItem(String menuItemId, MenuItem menuItemDetails) {
        return menuItemRepository.findById(menuItemId)
                .map(menuItem -> {
                    menuItem.setName(menuItemDetails.getName());
                    menuItem.setDescription(menuItemDetails.getDescription());
                    menuItem.setPrice(menuItemDetails.getPrice());
                    menuItem.setCategory(menuItemDetails.getCategory());
                    menuItem.setIsAvailable(menuItemDetails.getIsAvailable());
                    return menuItemRepository.save(menuItem);
                })
                .orElseThrow(() -> new RuntimeException("Menu item not found with id " + menuItemId));
    }

    @Transactional
    public void deleteMenuItem(String menuId, String menuItemId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Menu not found with id " + menuId));

        menu.getItems().removeIf(item -> item.getId().equals(menuItemId));
        menuRepository.save(menu);
        menuItemRepository.deleteById(menuItemId);
    }
}