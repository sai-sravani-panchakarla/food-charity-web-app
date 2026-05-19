package com.charity.food_service.controller;

import com.charity.food_service.entity.MenuItem;
import com.charity.food_service.service.MenuItemService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/menus")
@CrossOrigin(origins = "*")
public class MenuItemController {

    private static final Logger log = LoggerFactory.getLogger(MenuItemController.class);

    @Autowired
    private MenuItemService service;

    @GetMapping
    public ResponseEntity<List<MenuItem>> getAll() {
        log.info("GET /api/menus called");
        return ResponseEntity.ok(service.getAllMenuItems());
    }

    @GetMapping("/month/{month}")
    public ResponseEntity<List<MenuItem>> getByMonth(@PathVariable String month) {
        log.info("GET /api/menus/month/{} called", month);
        return ResponseEntity.ok(service.getByMonth(month));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<MenuItem>> getByMonthAndYear(
            @RequestParam String month,
            @RequestParam int year) {
        log.info("GET /api/menus/filter called with month={} year={}", month, year);
        return ResponseEntity.ok(service.getByMonthAndYear(month, year));
    }

    @PostMapping
    public ResponseEntity<MenuItem> addItem(@Valid @RequestBody MenuItem item) {
        log.info("POST /api/menus called for item: {}", item.getItemName());
        MenuItem saved = service.addMenuItem(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        log.info("DELETE /api/menus/{} called", id);
        service.deleteMenuItem(id);
        return ResponseEntity.ok("Menu item deleted successfully");
    }
}
