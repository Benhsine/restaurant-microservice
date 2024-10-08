package com.BigSolutions.FingerPrintApp.Restaurants_microservice.service;

import com.BigSolutions.FingerPrintApp.Restaurants_microservice.model.Menu;
import com.BigSolutions.FingerPrintApp.Restaurants_microservice.model.MenuItem;
import com.BigSolutions.FingerPrintApp.Restaurants_microservice.repository.MenuItemRepository;
import com.BigSolutions.FingerPrintApp.Restaurants_microservice.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final MenuItemRepository menuItemRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository, MenuItemRepository menuItemRepository) {
        this.menuRepository = menuRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public Optional<Menu> getMenuByRestaurantId(String restaurantId) {
        return menuRepository.findByRestaurantId(restaurantId);
    }

    @Transactional
    public Menu createOrUpdateMenu(String restaurantId, Menu menu) {
        menu.setId(restaurantId);
        return menuRepository.save(menu);
    }

    public List<MenuItem> getMenuItemsByCategory(String category) {
        return menuItemRepository.findByCategory(category);
    }

    @Transactional
    public MenuItem addMenuItem(String menuId, MenuItem menuItem) {
        Optional<Menu> menuOptional = menuRepository.findById(menuId);
        if (menuOptional.isPresent()) {
            Menu menu = menuOptional.get();
            menuItem = menuItemRepository.save(menuItem);
            menu.getItems().add(menuItem);
            menuRepository.save(menu);
            return menuItem;
        } else {
            throw new RuntimeException("Menu not found with id " + menuId);
        }
    }

    @Transactional
    public void removeMenuItem(String menuId, String menuItemId) {
        Optional<Menu> menuOptional = menuRepository.findById(menuId);
        if (menuOptional.isPresent()) {
            Menu menu = menuOptional.get();
            menu.getItems().removeIf(item -> item.getId().equals(menuItemId));
            menuRepository.save(menu);
            menuItemRepository.deleteById(menuItemId);
        } else {
            throw new RuntimeException("Menu not found with id " + menuId);
        }
    }
}