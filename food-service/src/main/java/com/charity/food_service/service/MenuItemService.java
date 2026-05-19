package com.charity.food_service.service;

import com.charity.food_service.entity.MenuItem;
import com.charity.food_service.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository repository;

    public List<MenuItem> getAllMenuItems() {
        return repository.findAll();
    }

    public List<MenuItem> getByMonth(String month) {
        return repository.findByMonth(month);
    }

    public List<MenuItem> getByMonthAndYear(String month, int year) {
        return repository.findByMonthAndServedYear(month, year);
    }

    public MenuItem addMenuItem(MenuItem item) {
        return repository.save(item);
    }

    public void deleteMenuItem(Long id) {
        repository.deleteById(id);
    }
}
