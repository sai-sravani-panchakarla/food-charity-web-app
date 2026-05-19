package com.charity.food_service.service;

import com.charity.food_service.entity.MenuItem;
import com.charity.food_service.exception.ResourceNotFoundException;
import com.charity.food_service.repository.MenuItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MenuItemService {

    private static final Logger log = LoggerFactory.getLogger(MenuItemService.class);

    @Autowired
    private MenuItemRepository repository;

    public List<MenuItem> getAllMenuItems() {
        log.info("Fetching all menu items");
        List<MenuItem> items = repository.findAll();
        log.info("Found {} menu items", items.size());
        return items;
    }

    public List<MenuItem> getByMonth(String month) {
        log.info("Fetching menu items for month: {}", month);
        return repository.findByMonth(month);
    }

    public List<MenuItem> getByMonthAndYear(String month, int year) {
        log.info("Fetching menu items for month: {} and year: {}", month, year);
        return repository.findByMonthAndServedYear(month, year);
    }

    public MenuItem addMenuItem(MenuItem item) {
        log.info("Adding new menu item: {}", item.getItemName());
        MenuItem saved = repository.save(item);
        log.info("Menu item saved with id: {}", saved.getId());
        return saved;
    }

    public void deleteMenuItem(Long id) {
        log.info("Deleting menu item with id: {}", id);
        if (!repository.existsById(id)) {
            log.error("Menu item not found with id: {}", id);
            throw new ResourceNotFoundException("Menu item not found with id: " + id);
        }
        repository.deleteById(id);
        log.info("Menu item deleted successfully with id: {}", id);
    }
}
