package com.charity.food_service.controller;

import com.charity.food_service.entity.MenuItem;
import com.charity.food_service.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/menus")
@CrossOrigin(origins = "*")
public class MenuItemController {

    @Autowired
    private MenuItemService service;

    @GetMapping
    public List<MenuItem> getAll() {
        return service.getAllMenuItems();
    }

    @GetMapping("/month/{month}")
    public List<MenuItem> getByMonth(@PathVariable String month) {
        return service.getByMonth(month);
    }

    @GetMapping("/filter")
    public List<MenuItem> getByMonthAndYear(
            @RequestParam String month,
            @RequestParam int year) {
        return service.getByMonthAndYear(month, year);
    }

    @PostMapping
    public MenuItem addItem(@RequestBody MenuItem item) {
        return service.addMenuItem(item);
    }

    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable Long id) {
        service.deleteMenuItem(id);
        return "Deleted successfully";
    }
}
